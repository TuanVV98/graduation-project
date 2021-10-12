package com.spring.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

	void save(String filename, MultipartFile file) throws IOException;

	byte[] downloadFtpFile(String filename) throws IOException;
}
