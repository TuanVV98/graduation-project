package com.spring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadFileResponse {
    private String fileName;
    private String fileDowloadUri;
    private String fileType;
    private long size;
}