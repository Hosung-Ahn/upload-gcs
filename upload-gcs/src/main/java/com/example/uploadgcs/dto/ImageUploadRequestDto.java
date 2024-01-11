package com.example.uploadgcs.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadRequestDto {
    private MultipartFile imageFile;

}
