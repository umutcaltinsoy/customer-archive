package com.altinsoy.customerarchive.controller;

import com.altinsoy.customerarchive.model.Attachment;
import com.altinsoy.customerarchive.model.dto.AttachmentDto;
import com.altinsoy.customerarchive.model.dto.AttachmentResponseDto;
import com.altinsoy.customerarchive.model.dto.GetAttachmentsDto;
import com.altinsoy.customerarchive.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public AttachmentResponseDto uploadFile(@RequestParam("file") MultipartFile file,
                                            @RequestParam(name = "id") Long id) throws Exception {
        Attachment attachment = null;
        String downloadURL = "";
        attachment = attachmentService.saveAttachment(file, id);
        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();
        log.info("Upload file {}", attachment.getFileName());
        return new AttachmentResponseDto(attachment.getFileName(),
                downloadURL,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        log.info("Download file...{}", attachment.getFileName());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

    @GetMapping("/files")
    public ResponseEntity<List<GetAttachmentsDto>> getCustomerAttachments(@RequestParam Long id) {
        List<GetAttachmentsDto> getAttachmentsDtos = attachmentService.getAttachmentByCustomerId(id);
        log.info("Geting customer's attachments {} ", getAttachmentsDtos);
        return ResponseEntity.ok(getAttachmentsDtos);
    }

    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileId) {
        attachmentService.deleteFile(fileId);
        log.info("Delete attachment : {}", fileId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getAllFiles")
    public ResponseEntity<List<AttachmentDto>> getAllAttachments() {
        List<AttachmentDto> attachmentDtos = attachmentService.getAllAttachments();
        log.info("Gettin all attachments {}", attachmentDtos);
        return ResponseEntity.ok(attachmentDtos);
    }
}
