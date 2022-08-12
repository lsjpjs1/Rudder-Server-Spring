package com.example.restapimvc.post.infra;

import com.example.restapimvc.common.FileMetaData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class S3FileUploadRepositoryTest {

    @Autowired
    private S3FileUploadRepository s3FileUploadRepository;
    @Test
    void getFileUploadUrls() {
        List<FileMetaData> fileMetaDataList = Arrays.asList(
                FileMetaData.builder()
                        .fileName("abcd")
                        .build()
        );
        s3FileUploadRepository.getFileUploadUrls(fileMetaDataList).forEach(uploadUrl -> System.out.println(uploadUrl.getUrl()));
    }
}