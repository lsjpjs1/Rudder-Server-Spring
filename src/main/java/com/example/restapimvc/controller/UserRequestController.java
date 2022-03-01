package com.example.restapimvc.controller;

import com.example.restapimvc.domain.UserRequest;
import com.example.restapimvc.dto.UserRequestDTO;
import com.example.restapimvc.service.UserRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-requests")
@RequiredArgsConstructor
public class UserRequestController {

    private final UserRequestService userRequestService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserRequest> createUserRequest(@RequestBody UserRequestDTO.CreateUserRequestRequest createUserRequestRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userRequestService.createUserRequestResponse(createUserRequestRequest));
    }

}
