package com.test.pos.controller;

import com.test.pos.dto.CommentDto;
import com.test.pos.entity.Post;
import com.test.pos.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/{postid}")
    public CommentDto.Find getAllComment(@PathVariable CommentDto.FindRequest postid) {
        return commentService.getAllComment(postid);
    }
    // 특정 댓글 조회
    @GetMapping("/search")
    public List<Comment> searchCommentsByContent(@RequestParam String contents) {
        return commentService.findCommentsByContent(contents);
    }
    // 새 댓글 생성
    @PostMapping("/post")
    public CommentDto.Response createComment(@RequestBody CommentDto.Request comment){
        return commentService.createComment(comment);
    }
    // 댓글 수정
    @PutMapping("/{id}")
    public Comment updatecomment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        return commentService.updateComment(id, commentDetails);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
