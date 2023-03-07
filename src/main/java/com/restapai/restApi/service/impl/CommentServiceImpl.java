package com.restapai.restApi.service.impl;

import com.restapai.restApi.entity.Comment;
import com.restapai.restApi.utils.request.CommentDTO;
import com.restapai.restApi.repository.CommentRepository;
import com.restapai.restApi.service.CommentService;
import com.restapai.restApi.utils.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    @Override
    public List<CommentResponseDTO> getAll() {
        List<Comment> comments=commentRepository.findAll();
        return comments.stream().map(c->mapperToDTO(c)).collect(Collectors.toList()) ;
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {
        var comment= commentRepository.findById(id).get();

        return Optional.of(mapperToDTO(comment));
    }

    @Override
    public CommentResponseDTO save(CommentDTO dto) {

        Comment cmt= new Comment();
        cmt.setName(dto.getName());
        cmt.setEmail(dto.getEmail());
        cmt.setBody(dto.getBody());
        //cmt.setPost(dto.getPost());
        var saveComment= commentRepository.save(cmt);
        return mapperToDTO(commentRepository.save(saveComment));
    }

    @Override
    public CommentResponseDTO update(CommentDTO dto, Long id) {
        var comment= commentRepository.findById(id).get();
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        comment.setName(dto.getName());

        return mapperToDTO(commentRepository.save(comment));
    }

    @Override
    public String delete(Long id) {
        commentRepository.deleteById(id);
        return "Apagado";
    }

    private CommentResponseDTO mapperToDTO(Comment comment){
        var dto= new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setEmail(comment.getEmail());
        dto.setName(comment.getName());
        dto.setPost(comment.getPost());
        return dto;
    }

}
