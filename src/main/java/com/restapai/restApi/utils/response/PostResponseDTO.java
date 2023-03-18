package com.restapai.restApi.utils.response;


import com.restapai.restApi.entity.Comment;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class PostResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Integer maximumOfComments;
    private Set<CommentResponseDTO> comments;
}
