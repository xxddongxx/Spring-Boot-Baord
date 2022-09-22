package com.xxddongxx.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileDto {

    private String fileName;
    private String saveFileName;
    private String filePath;
    private String contentType;
    private Long size;


}
