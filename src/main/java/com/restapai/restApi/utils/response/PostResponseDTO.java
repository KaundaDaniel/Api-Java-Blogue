package com.restapai.restApi.utils.response;


import com.restapai.restApi.entity.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class PostResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentResponseDTO> comments;
}
