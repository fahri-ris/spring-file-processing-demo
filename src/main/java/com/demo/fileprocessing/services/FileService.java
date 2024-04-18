package com.demo.fileprocessing.services;

import com.demo.fileprocessing.dtos.FileResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    FileResponseDto uploadFile(MultipartFile file);
    List<FileResponseDto> getAllFiles();
    FileResponseDto getFiles(Long fileId);
}
