package com.oob.domain.payload.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

public class PostRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private MultipartFile image;
}
