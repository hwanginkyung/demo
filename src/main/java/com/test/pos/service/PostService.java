package com.test.pos.service;
import com.test.pos.dto.CommentDto;
import com.test.pos.dto.PostDto;
import com.test.pos.entity.Post;
import com.test.pos.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.entry.CollectionCacheEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public PostDto.Response createPost(PostDto.Request post) {
        Post newPost = Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .build();
        Post savedPost = postRepository.save(newPost);
        return PostDto.Response.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .writer(savedPost.getWriter())
                .build();
    }
    public PostDto.Response getPost(int postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new NoSuchElementException());

        List<CommentDto.Response> commentList=post.getCommentList().stream()
                .map(comment -> CommentDto.Response.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .writer(comment.getWriter())
                        .build())
                .collect(Collectors.toList());
        return PostDto.Response.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .commentList(commentList)
                .build();
    }
    public List<PostDto.ResponseList> getPostList(){
        List<Post> postList = postRepository.findAll();

        return postList.stream()
                .map(post->PostDto.ResponseList.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .writer(post.getWriter())
                        .commentCount((post.getCommentList().size()))
                        .build()
                ).collect(Collectors.toList());
    }
    public PostDto.Response updatePost(int postId, PostDto.Request updatedpost) {
        Post post= postRepository.findById(postId)
                .orElseThrow(()->new NoSuchElementException());
        post.update(updatedpost);
        Post savedPost = postRepository.save(post);
        List<CommentDto.Response> commentList=post.getCommentList().stream()
                .map(comment ->CommentDto.Response.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .writer(comment.getWriter())
                        .build())
                .collect(Collectors.toList());

        return PostDto.Response.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .writer(savedPost.getWriter())
                .commentList(commentList)
                .build();
    }


}
