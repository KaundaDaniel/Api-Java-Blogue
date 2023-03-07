package com.restapai.restApi.service;

import com.restapai.restApi.entity.Comment;
import com.restapai.restApi.utils.request.CommentDTO;
import com.restapai.restApi.utils.response.CommentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List <CommentResponseDTO> getAll();
    Optional<CommentResponseDTO> findById(Long id);
    CommentResponseDTO save(CommentDTO dto);

    CommentResponseDTO update(CommentDTO dto, Long id);
    String delete(Long id);
}
