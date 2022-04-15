package com.altinsoy.customerarchive.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentResponseDto {

    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;

}
