package com.xxddongxx.board.model;

import com.xxddongxx.board.utils.BaseTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "imageFile")
public class UploadFile extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileNo;

    private String fileName;
    private String saveFileName;

    private String filePath;
    private String contentType;
    private Long size;
}
