package com.example.restapimvc.post.command.application;

import com.example.restapimvc.common.FileMetaData;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.FileUploadRepository;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.domain.PostImage;
import com.example.restapimvc.post.command.domain.PostRepository;
import com.example.restapimvc.post.command.dto.FileDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.util.RandomNumber;
import com.example.restapimvc.util.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostImageUploadService {
    private final FileUploadRepository fileUploadRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Transactional
    public FileDto.UploadUrlsWrapper getImageUploadUrl(UserInfo userInfo,WritePostDto.ImageUploadUrlRequest imageUploadUrlRequest) {
        if (imageUploadUrlRequest.getImageMetaData() == null) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        if (imageUploadUrlRequest.getImageMetaData().isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        Post post = postRepository.findById(imageUploadUrlRequest.getPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!post.getUserId().equals(userInfo.getUserId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        List<String> fileNames = new ArrayList<>();
        for (int i=0; i<imageUploadUrlRequest.getImageMetaData().size();i++) {
           fileNames.add(new Date().getTime() + RandomNumber.generateRandomCode(6));
        }
        List<FileMetaData> imageMetaData = imageUploadUrlRequest.getImageMetaData();
        for (int i=0; i<imageMetaData.size();i++) {
            imageMetaData.get(i).setFileName(fileNames.get(i));
        }

        post.appendPostImages(fileNames);
        postRepository.save(post);
        return FileDto.UploadUrlsWrapper.builder().uploadUrls(fileUploadRepository.getFileUploadUrls(imageMetaData)).build();
    }

    @Transactional
    public WritePostDto.CompleteImageUploadResponse completeImageUpload(UserInfo userInfo, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!post.getUserId().equals(userInfo.getUserId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        post.completeImageUpload();
        postRepository.save(post);
        return postMapper.entityToCompleteImageUploadResponse(post);
    }
}
