package com.example.restapimvc.common;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FileMetaData {
    @Setter
    private String fileName;
    private String contentType;
}
