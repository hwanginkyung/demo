package com.test.pos.service;


import com.test.pos.dto.CommentDto;
import com.test.pos.dto.PostDto;
import com.test.pos.entity.Comment;
import com.test.pos.entity.Post;
import com.test.pos.repository.CommentRepository;
import com.test.pos.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    public CommentDto.Response createComment(CommentDto.Request comment) {
        Comment newComment = Comment.builder()
                .content(comment.getContent())
                .writer(comment.getWriter())
                .build();
        Comment savedComment = commentRepository.save(newComment);
        return CommentDto.Response.builder()
                .id(savedComment.getId())
                .content(savedComment.getContent())
                .writer(savedComment.getWriter())
                .build();
    }
    public CommentDto.Find getAllComment(CommentDto.FindRequest id) {
        int ids= id.getId();
        Post post = postRepository.findById(ids)
                .orElseThrow(()->new NoSuchElementException());
        List<CommentDto.Response> commentList= post.getCommentList().stream()
                .map(comment -> CommentDto.Response.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .writer(comment.getWriter())
                        .build())
                .collect(Collectors.toList());
        return CommentDto.Find.builder()
                .comments(commentList)
                .build();
    }
    public CommentDto.Response updateComment(int commentid, CommentDto.Request updatecomment) {
        Comment comment= commentRepository.findById(commentid)
                .orElseThrow(()->new NoSuchElementException());
    }

}
