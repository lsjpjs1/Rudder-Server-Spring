package com.example.restapimvc.post.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.FileUploadRepository;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.domain.PostRepository;
import com.example.restapimvc.post.command.dto.FileDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.util.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class PostImageUploadService {
    private final FileUploadRepository fileUploadRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public FileDto.UploadUrlsWrapper getImageUploadUrl(WritePostDto.ImageUploadUrlRequest imageUploadUrlRequest) {
        if (imageUploadUrlRequest.getImageMetaData() == null) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        if (imageUploadUrlRequest.getImageMetaData().isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        return FileDto.UploadUrlsWrapper.builder().uploadUrls(fileUploadRepository.getFileUploadUrls(imageUploadUrlRequest.getImageMetaData())).build();
    }

    @Transactional
    public WritePostDto.CompleteImageUploadResponse completeImageUpload(UserInfo userInfo, WritePostDto.CompleteImageUploadRequest completeImageUploadRequest) {
        if (completeImageUploadRequest.getFileNames() == null) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        if (completeImageUploadRequest.getFileNames().isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        Post post = postRepository.findById(completeImageUploadRequest.getPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!post.getUserId().equals(userInfo.getUserId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        post.completeImageUpload(completeImageUploadRequest.getFileNames());
        postRepository.save(post);
        return postMapper.entityToCompleteImageUploadResponse(post);
    }
}
