package com.example.restapimvc.comment.command.application;

import com.example.restapimvc.comment.command.domain.Comment;
import com.example.restapimvc.comment.command.domain.CommentRepository;
import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.domain.PostRepository;
import com.example.restapimvc.util.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManipulateCommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public CommentDto.CommonCommentResponse writeComment(UserInfo userInfo, CommentDto.WriteCommentRequest writeCommentRequest) {
        writeCommentRequest.setAllUserInfo(userInfo);
        Post post = postRepository.findById(writeCommentRequest.getPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        List<Comment> comments = commentRepository.findAllByPost(post);
        Comment comment = Comment.of(writeCommentRequest, comments, userInfo, post);
        post.addComment();
        postRepository.save(post);
        commentRepository.save(comment);
        entityManager.refresh(comment);
        return commentMapper.entityToWriteCommentResponse(comment);
    }

    @Transactional
    public CommentDto.CommonCommentResponse editComment(UserInfo userInfo, CommentDto.EditCommentRequest editCommentRequest) {
        editCommentRequest.setAllUserInfo(userInfo);
        Comment comment = commentRepository.findById(editCommentRequest.getCommentId())
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        if (!comment.getUserInfo().getUserInfoId().equals(userInfo.getUserInfoId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        comment.editCommentBody(editCommentRequest.getCommentBody());
        commentRepository.save(comment);
        return commentMapper.entityToWriteCommentResponse(comment);
    }

    @Transactional
    public void deleteComment(UserInfo userInfo, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        if (!comment.getUserInfo().getUserInfoId().equals(userInfo.getUserInfoId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        comment.delete();
        commentRepository.save(comment);
    }

    @Transactional
    public void likeComment(UserInfo userInfo, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        comment.like(userInfo);
        commentRepository.save(comment);
    }

}
