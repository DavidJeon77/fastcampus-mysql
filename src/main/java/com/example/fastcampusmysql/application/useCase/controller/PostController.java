package com.example.fastcampusmysql.application.useCase.controller;


import com.example.fastcampusmysql.application.useCase.CreatePostLikeUsecase;
import com.example.fastcampusmysql.application.useCase.CreatePostUsecase;
import com.example.fastcampusmysql.application.useCase.GetTimelinePostsUseCase;
import com.example.fastcampusmysql.domain.member.dto.PostDto;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    final private PostWriteService postWriteService;
    final private PostReadService postReadService;
    final private GetTimelinePostsUseCase getTimelinePostsUseCase;

    final private CreatePostUsecase createPostUsecase;

    final private CreatePostLikeUsecase createPostLikeUsecase;

    @PostMapping("")
    public Long create(PostCommand command) {
        return createPostUsecase.execute(command);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        return postReadService.getDailyPostCounts(request);
    }

    @GetMapping("/members/{memberId}")
    public Page<PostDto> getPosts(@PathVariable Long memberId, Pageable pageable) {
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPostsByCursor(@PathVariable Long memberId, CursorRequest cursorRequest) {
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/member/{memberId}/timeline")
    public PageCursor<Post> getTimeline(@PathVariable Long memberId, CursorRequest cursorRequest) {
        return getTimelinePostsUseCase.executeByTimeline(memberId, cursorRequest);
    }

    @PostMapping("/{postId}/like/v1")
    public void likePost(@PathVariable Long postId) {
//        postWriteService.likePost(postId);
        postWriteService.likePostWithOptimisticLock(postId);
    }

    @PostMapping("/{postId}/like/v2")
    public void likePostV2(@PathVariable Long postId, @RequestParam Long memberId) {
//        postWriteService.likePost(postId);
//        postWriteService.likePostWithOptimisticLock(postId);
        createPostLikeUsecase.execute(postId, memberId);
    }

}
