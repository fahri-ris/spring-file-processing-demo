package com.demo.fileprocessing.controllers;

import com.demo.fileprocessing.dtos.FileResponseDto;
import com.demo.fileprocessing.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping()
    public ResponseEntity<FileResponseDto> responseEntity(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(fileService.uploadFile(file));
    }

    @GetMapping(("/{fileId}"))
    public ResponseEntity<FileResponseDto> getFiles(@PathVariable Long fileId) {
        return ResponseEntity.ok(fileService.getFiles(fileId));
    }

    @GetMapping()
    public ResponseEntity<List<FileResponseDto>> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }
}
