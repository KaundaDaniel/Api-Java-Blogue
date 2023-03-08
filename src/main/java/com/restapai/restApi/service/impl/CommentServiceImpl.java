package com.restapai.restApi.service.impl;

import com.restapai.restApi.entity.Comment;
import com.restapai.restApi.entity.Post;
import com.restapai.restApi.errors.ResourceNotFoundException;
import com.restapai.restApi.repository.PostRepository;
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
    private final PostRepository postRepository;
    @Override
    public List<CommentResponseDTO> getAll() {
        List<Comment> comments=commentRepository.findAll();
        return comments.stream().map(c->mapperToCommentDTO(c)).collect(Collectors.toList()) ;
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {

        commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comentario não encontrado!"));
        var comment= commentRepository.findById(id).get();

        return Optional.of(mapperToCommentDTO(comment));
    }

    @Override
    public CommentResponseDTO save(CommentDTO dto) {
        Post p= postRepository.findById(dto.getPostId()).orElseThrow(()-> new ResourceNotFoundException("Post não encontrado!" +dto.getPostId()));
        Comment cmt= new Comment();
        cmt.setName(dto.getName());
        cmt.setEmail(dto.getEmail());
        cmt.setBody(dto.getBody());
        cmt.setPost(p);
        //cmt.setPost(dto.getPost());
        var saveComment= commentRepository.save(cmt);
        return mapperToCommentDTO(commentRepository.save(saveComment));
    }

    @Override
    public CommentResponseDTO update(CommentDTO dto, Long id) {
        var comment= commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comentario não encontrado para actualizar!"));
        var p= postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post não encontrado!"));
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        comment.setName(dto.getName());
        comment.setPost(p);

        return mapperToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public String delete(Long id) {
        commentRepository.deleteById(id);
        return "Apagado";
    }

    public static CommentResponseDTO mapperToCommentDTO(Comment comment){
        var dto= new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setEmail(comment.getEmail());
        dto.setName(comment.getName());
        dto.setPostTitle(comment.getPost().getTitle());
        return dto;
    }

}
