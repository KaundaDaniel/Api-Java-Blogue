package com.restapai.restApi.utils.request;

import com.restapai.restApi.entity.Post;
import lombok.Data;

@Data
public class CommentDTO {

    private String name;
    private String email;
    private String body;
    private Long postId;
}
