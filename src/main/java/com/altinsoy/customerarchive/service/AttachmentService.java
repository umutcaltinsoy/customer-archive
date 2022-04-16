package com.altinsoy.customerarchive.service;

import com.altinsoy.customerarchive.model.Attachment;
import com.altinsoy.customerarchive.model.dto.GetAttachmentsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file, Long id) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;

    List<GetAttachmentsDto> getAttachmentByCustomerId(Long id);
}
