package com.demo.fileprocessing.services;

import com.demo.fileprocessing.dtos.FileResponseDto;
import com.demo.fileprocessing.models.Documents;
import com.demo.fileprocessing.repositories.DocumentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    DocumentRepository documentRepository;

    @Autowired
    public FileServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public FileResponseDto uploadFile(MultipartFile file) {
        try{
            String requestFileName = file.getOriginalFilename();
            String requestFileType = file.getContentType();
            byte[] requestFileData = file.getBytes();

            Documents documents = new Documents().builder()
                    .fileName(requestFileName)
                    .fileType(requestFileType)
                    .file(requestFileData)
                    .build();
            Documents savedDocument = documentRepository.save(documents);

            FileResponseDto fileResponseDto = new FileResponseDto().builder()
                    .fileId(savedDocument.getFileId())
                    .fileName(savedDocument.getFileName())
                    .fileType(savedDocument.getFileType())
                    .fileData(savedDocument.getFile())
                    .build();
            return fileResponseDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public List<FileResponseDto> getAllFiles() {
        try{
            List<Documents> listDoc = documentRepository.findAll();

            List<FileResponseDto> fileResponseDtos = listDoc.stream().map(document -> new FileResponseDto().builder()
                            .fileId(document.getFileId())
                            .fileName(document.getFileName())
                            .fileType(document.getFileType())
                            .fileSize((long) document.getFile().length)
                            .fileData(document.getFile())
                            .build())
                            .collect(Collectors.toList());
            return fileResponseDtos;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public FileResponseDto getFiles(Long fileId) {
        try{
            Documents documents = documentRepository.findById(fileId).orElseThrow(
                    () -> new EntityNotFoundException("File not found"));

            return new FileResponseDto().builder()
                    .fileId(documents.getFileId())
                    .fileName(documents.getFileName())
                    .fileType(documents.getFileType())
                    .fileSize((long)documents.getFile().length)
                    .fileData(documents.getFile())
                    .build();

        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
