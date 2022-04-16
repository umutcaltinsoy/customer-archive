package com.altinsoy.customerarchive.service.impl;

import com.altinsoy.customerarchive.model.Attachment;
import com.altinsoy.customerarchive.model.Customer;
import com.altinsoy.customerarchive.model.dto.GetAttachmentsDto;
import com.altinsoy.customerarchive.repository.AttachmentRepository;
import com.altinsoy.customerarchive.service.AttachmentService;
import com.altinsoy.customerarchive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final CustomerService customerService;
    private final ModelMapper mapper;

    @Override
    public Attachment saveAttachment(MultipartFile file, Long id) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Customer customer = customerService.getById(id);

        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence " + fileName);
            }
            Attachment attachment = Attachment.builder()
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .data(file.getBytes())
                    .customer(customer)
                    .build();
            return attachmentRepository.save(attachment);
        } catch (Exception e) {
            throw new Exception("Could not save file : " + fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id : " + fileId));
    }

    @Override
    public List<GetAttachmentsDto> getAttachmentByCustomerId(Long id) {
        return attachmentRepository.findAttachmentByCustomerId(id).stream().map(attachment
                -> mapper.map(attachment, GetAttachmentsDto.class)).collect(Collectors.toList());
    }
}
