package com.xxddongxx.board.controller;

import com.xxddongxx.board.model.UploadFile;
import com.xxddongxx.board.service.ImageUplloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageUploadController {

    @Autowired
    ImageUplloadService imageUploadService;

    @Autowired
    ResourceLoader resourceLoader;

    @PostMapping("/image")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) {
        try {
            UploadFile uploadFile = this.imageUploadService.store(file);
            return ResponseEntity.ok().body("/image/" + uploadFile.getFileNo());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/image/{fileNo}")
    public ResponseEntity<?> serveFile(@PathVariable Long fileNo) {
        try {
            UploadFile uploadFile = this.imageUploadService.load(fileNo);
            Resource resource = resourceLoader.getResource("file:" + uploadFile.getFilePath());
            return ResponseEntity.ok().body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
