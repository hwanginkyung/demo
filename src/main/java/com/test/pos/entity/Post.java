package com.test.pos.entity;

import com.test.pos.dto.PostDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "writer")
    private String writer;
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;
    public void update(PostDto.Request post){
        this.title = post.getTitle();
        this.content = post.getContent();
    }

}
