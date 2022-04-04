package com.example.restapimvc.controller;

import com.example.restapimvc.dto.SchoolDTO;
import com.example.restapimvc.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    /**
     * /signupin/schoolList
     * @return 200, List<SchoolForResponse> schools{
     *                  List[
     *                      Long schoolId,
     *                      String schoolName,
     *                      ]
     *              }
     *              예시) {"schools":[{"schoolId":1,"schoolName":"Waseda University"},{"schoolId":2,"schoolName":"Korea University"}]}
     */
    @GetMapping(value = "/schools")
    public ResponseEntity<SchoolDTO.GetSchoolsResponse> getSchools() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(schoolService.getSchools())
                ;
    }
}
