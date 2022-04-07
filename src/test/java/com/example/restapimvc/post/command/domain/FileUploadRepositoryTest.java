package com.example.restapimvc.post.command.domain;

import com.example.restapimvc.common.FileMetaData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class FileUploadRepositoryTest {
    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Test
    void getFileUploadUrls() {
        List<FileMetaData> fileMetaData = new ArrayList<>();
        fileMetaData.add(new FileMetaData("test","text/plain"));
        fileMetaData.add(new FileMetaData("hey2","text/plain"));
        fileUploadRepository.getFileUploadUrls(fileMetaData)
                .stream()
                .forEach((s)->System.out.println(s));

    }
}