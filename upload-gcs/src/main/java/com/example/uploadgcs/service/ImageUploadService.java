package com.example.uploadgcs.service;

import com.example.uploadgcs.dto.ImageUploadRequestDto;
import com.google.cloud.storage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageUploadService {
    private final Storage storage;
    private final String GCS_URL = "https://storage.googleapis.com/";

    @Value("${gcs.bucket.name}")
    private String bucketName;

    public String uploadImage(ImageUploadRequestDto imageUploadRequestDto)  {
        MultipartFile multipartFile = imageUploadRequestDto.getImageFile();
        String originalFilename = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();

        // Generate a unique identifier for this file
        String uuid = UUID.randomUUID().toString();
        String blobName = "images/" + uuid + "-" + originalFilename;

        // Prepare the blobId and blobInfo
        BlobId blobId = BlobId.of(bucketName, blobName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();

        // Upload the file to Google Cloud Storage
        Blob blob = null;
        try {
            blob = storage.create(blobInfo, multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Return the public URL of this file
        return GCS_URL + blob.getBucket() + "/" + blob.getName();
    }
}
