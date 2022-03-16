package com.example.restapimvc.service;

import com.example.restapimvc.dto.NoticeDTO;
import com.example.restapimvc.enums.NoticeMention;
import com.example.restapimvc.enums.UserInfoOsType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    public NoticeDTO.NoticeResponse getNotice(NoticeDTO.NoticeRequest noticeRequest) {
        NoticeDTO.NoticeResponse noticeResponse = NoticeDTO.NoticeResponse.builder().noticeBody(NoticeMention.UPDATE.body).build();
        if(UserInfoOsType.isNewestVersion(noticeRequest)){
            noticeResponse.setNoticeBody(NoticeMention.DEFAULT.body);
        }
        return noticeResponse;
    }


}
