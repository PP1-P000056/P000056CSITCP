package com.example.project000056.security.services;

import com.example.project000056.model.FileUpload;
import com.example.project000056.model.Order;
import com.example.project000056.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
    public class FileStorageService {
    @Autowired
    private FileRepository fileRepository;

    public FileUpload store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileUpload upload = new FileUpload(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(upload);
    }

    public FileUpload getFile(Long id) {
        return fileRepository.findById(String.valueOf(id)).get();
    }

    public Stream<FileUpload> getAllFiles() {
        return fileRepository.findAll().stream();
    }
    }

