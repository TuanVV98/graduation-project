package com.spring.controller.v1.file;

import com.spring.service.file.FileStorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileStorageService fileStorageService;

    @Autowired
    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile image) throws IOException {
        String filename = null;
        if (!image.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            filename = uuid + "." + extension;
            this.fileStorageService.save(filename,image);
        }
        return ResponseEntity.status(HttpStatus.OK).body(filename);
    }

    // hiển thị ảnh
    @GetMapping(value = "/download/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@RequestParam(required = false) String filename) throws IOException {
        System.out.println("file name :" + filename);
        return this.fileStorageService.downloadFtpFile(filename);
    }
}