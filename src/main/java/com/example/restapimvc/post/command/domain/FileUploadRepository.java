package com.example.restapimvc.post.command.domain;

import com.example.restapimvc.common.FileMetaData;
import com.example.restapimvc.post.command.dto.FileDto;

import java.util.List;


public interface FileUploadRepository {
    List<FileDto.UploadUrl> getFileUploadUrls(List<FileMetaData> imageMetaData);

}
