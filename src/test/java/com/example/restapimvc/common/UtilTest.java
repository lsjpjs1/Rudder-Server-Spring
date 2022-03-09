package com.example.restapimvc.common;

import com.example.restapimvc.domain.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class UtilTest {
    @Test
    public void objectMapperTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = UserInfo.builder()
                .userInfoId(1L)
                .userId("123")
                .build();
        HashMap<String, Object> map = objectMapper.convertValue(userInfo, HashMap.class);

        for(String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }
}
