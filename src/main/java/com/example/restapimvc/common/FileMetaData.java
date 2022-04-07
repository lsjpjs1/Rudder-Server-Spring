package com.example.restapimvc.common;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FileMetaData {
    private String fileName;
    private String contentType;
}
