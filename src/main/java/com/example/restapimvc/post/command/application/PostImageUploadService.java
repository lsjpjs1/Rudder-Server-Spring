package com.example.restapimvc.post.command.application;

import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.FileUploadRepository;
import com.example.restapimvc.post.command.dto.FileDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostImageUploadService {
    private final FileUploadRepository fileUploadRepository;

    public FileDto.UploadUrlsWrapper getImageUploadUrl(WritePostDto.ImageUploadUrlRequest imageUploadUrlRequest) {
        if (imageUploadUrlRequest.getImageMetaData() == null) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        if (imageUploadUrlRequest.getImageMetaData().isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        return FileDto.UploadUrlsWrapper.builder().uploadUrls(fileUploadRepository.getFileUploadUrls(imageUploadUrlRequest.getImageMetaData())).build();
    }
}
