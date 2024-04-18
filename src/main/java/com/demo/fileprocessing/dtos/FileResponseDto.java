package com.demo.fileprocessing.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponseDto {
    private Long fileId;
    private String fileType;
    private String fileName;
    private Long fileSize;
    private byte[] fileData;
}
