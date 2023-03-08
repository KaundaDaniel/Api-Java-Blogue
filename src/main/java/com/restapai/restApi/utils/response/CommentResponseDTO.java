package com.restapai.restApi.utils.response;

import com.restapai.restApi.entity.Post;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CommentResponseDTO {


    private Long id;


    private String name;

    private String email;

    private String body;

    private String postTitle;
}
