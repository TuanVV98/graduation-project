package com.spring.service.file;


import com.spring.exception.NotParsableContentException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class FilesStorageServiceImpl implements FileStorageService {

    @Value("${ftp.server.hostname}")
    private String hostname;

    @Value("${ftp.server.port}")
    private int port;

    @Value("${ftp.server.username}")
    private String username;

    @Value("${ftp.server.password}")
    private String password;

    @Override
    public void save(String filename, MultipartFile file) throws IOException {

        FTPClient ftpClient = new FTPClient();
        if (file != null) {
            try {
                if (file.getOriginalFilename().contains("..")) {
                    throw new NotParsableContentException("Sorry! Filename contains invalid path sequence " + file.getOriginalFilename());
                }

                ftpClient.connect(hostname, port);
                if (ftpClient.login(username, password)) {
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                    ftpClient.storeFile(filename, file.getInputStream());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
    }

    @Override
    public byte[] downloadFtpFile(String filename) throws IOException {

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(hostname, port);
            if (ftpClient.login(username, password)) {
                System.out.println("connecting...");
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
                    ftpClient.retrieveFile(filename, outputStream);
                    return outputStream.toByteArray();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ftpClient.logout();
            ftpClient.disconnect();
        }
        return null;
    }
}
