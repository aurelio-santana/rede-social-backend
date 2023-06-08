package com.sysmap.socialNetwork.services.post;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Data
public class CreatePostRequest {
    public String title;
    public String content;
    public UUID userId;
    public List<MultipartFile> fileList;
}


