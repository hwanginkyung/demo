package com.test.pos.dto;

import com.test.pos.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CommentDto {
    @Getter
    @Setter
    public static class Request{
        private String content;
        private String writer;
    }
    @Getter
    @Setter
    @Builder
    public static class Response{
        private int id;
        private String content;
        private String writer;
    }
    @Getter
    @Setter
    @Builder
    public static class FindRequest{
        private int id;
    }
    @Getter
    @Builder
    public static class Find{
        private List<CommentDto.Response> comments;
    }
}
