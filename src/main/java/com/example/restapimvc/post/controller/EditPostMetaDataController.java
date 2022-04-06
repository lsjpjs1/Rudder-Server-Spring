package com.example.restapimvc.post.controller;

import com.example.restapimvc.post.command.application.EditPostMetaDataService;
import com.example.restapimvc.post.command.application.EditPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EditPostMetaDataController {

    private final EditPostMetaDataService editPostMetaDataService;

    /**
     * @param postId Long
     * @return 204
     * @throws 404 POST_NOT_FOUND 존재하지 않는 postId
     */
    @PatchMapping(value = "/posts/{postId}/view-count")
    public ResponseEntity viewPost(@PathVariable Long postId) {
        editPostMetaDataService.viewPost(postId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }
}
