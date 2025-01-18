package com.test.pos.dto;

import com.test.pos.entity.Comment;
import lombok.*;

import java.util.List;

public class PostDto {
    @Getter
    @Setter
    public static class Request{
        private String title;
        private String content;
        private String writer;
    }
    @Getter
    @Setter
    @Builder
    public static class Response{
        private int id;
        private String title;
        private String content;
        private String writer;
        private List<CommentDto.Response> commentList;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseList{
        private int id;
        private String title;
        private String writer;
        private int commentCount;
    }
}
