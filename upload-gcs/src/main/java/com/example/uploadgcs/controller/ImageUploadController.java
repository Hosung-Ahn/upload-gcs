package com.example.uploadgcs.controller;

import com.example.uploadgcs.dto.ImageUploadRequestDto;
import com.example.uploadgcs.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageUploadController {
    private final ImageUploadService imageUploadService;

    @GetMapping("/upload-image")
    public ResponseEntity<String> uploadImage(ImageUploadRequestDto imageUploadRequestDto) {
        String url = imageUploadService.uploadImage(imageUploadRequestDto);
        return ResponseEntity.ok(url);
    }
}
