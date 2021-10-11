package com.spring.service.file;

import com.spring.config.FileStorageProperties;
import com.spring.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public interface FileStorageService {

    void save(String filename,MultipartFile file) throws IOException;

    byte[] downloadFtpFile(String filename) throws IOException;
}
