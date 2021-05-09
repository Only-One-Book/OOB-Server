package com.oob.controller;

import com.oob.domain.payload.request.PostRequest;
import com.oob.domain.payload.response.ApplicationListResponse;
import com.oob.domain.payload.response.PostResponse;
import com.oob.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void writePost(@ModelAttribute @Valid PostRequest request) {
        postService.write(request);
    }

    @GetMapping
    public ApplicationListResponse postList(Pageable pageable) {
        return postService.postList(pageable);
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Integer postId) {
        return postService.getPost(postId);
    }

    @PutMapping("/{postId}")
    public void modifyPost(@ModelAttribute @Valid PostRequest postRequest, @PathVariable Integer postId) {
        postService.modifyPost(postRequest, postId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
    }
}
