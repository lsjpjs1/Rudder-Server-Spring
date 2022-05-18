package com.example.restapimvc.controller;

import com.example.restapimvc.dto.NoticeDTO;
import com.example.restapimvc.service.NoticeService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notice")
@RequiredArgsConstructor
@Api( tags = "공지사항 관련")
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * Legacy: /signupin/getNotice
     * @param noticeRequest String os, String version
     * @return 200, String noticeBody
     */
    @GetMapping
    public ResponseEntity<NoticeDTO.NoticeResponse> getNotice(@ModelAttribute NoticeDTO.NoticeRequest noticeRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(noticeService.getNotice(noticeRequest))
                ;
    }
}
