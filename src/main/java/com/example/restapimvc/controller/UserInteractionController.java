package com.example.restapimvc.controller;

import com.example.restapimvc.dto.UserInteractionDTO;
import com.example.restapimvc.service.UserInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserInteractionController {
    private final UserInteractionService userInteractionService;

    @PostMapping(value = "/blockUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInteractionDTO.BlockUserResponse> blockUser(@RequestBody UserInteractionDTO.BlockUserRequest blockUserRequest) {
        UserInteractionDTO.BlockUserResponse blockUserResponse = userInteractionService.blockUser(blockUserRequest);
        return ResponseEntity.ok(blockUserResponse);
    }
}
