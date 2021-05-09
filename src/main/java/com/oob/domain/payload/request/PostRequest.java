package com.oob.domain.payload.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class PostRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private MultipartFile image;
}
