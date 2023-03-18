package com.restapai.restApi.service.impl;

import com.restapai.restApi.entity.Post;
import com.restapai.restApi.errors.ResourceNotFoundException;
import com.restapai.restApi.repository.PostRepository;
import com.restapai.restApi.service.PostService;
import com.restapai.restApi.utils.request.PostDTO;
import com.restapai.restApi.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.restapai.restApi.service.impl.CommentServiceImpl.mapperToCommentDTO;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Override
    public List<PostResponseDTO> getAll() {
        return postRepository.findAll().stream().map(p->mapperToPostDTO(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<PostResponseDTO> findById(Long id) {

        postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Materia não encontrada"+ id));
        return Optional.of(mapperToPostDTO(postRepository.findById(id).get()));
    }

    @Override
    public PostResponseDTO save(PostDTO dto) {
        Post p = new Post();
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setContent(dto.getContent());
        p.setMaximumOfComments(dto.getMaximumOfComments());
        Post saved= postRepository.save(p);

        return mapperToPostDTO(saved);
    }

    @Override
    public PostResponseDTO update(PostDTO dto, Long id) {

        Post p= postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Materia nao encontrada " +id));
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setContent(dto.getContent());

        return mapperToPostDTO(p);
    }

    @Override
    public String delete(Long id) {
        postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Matria não encontrada para apagar!"));
        postRepository.deleteById(id);
        return "Apagado com Sucesso!";
    }

    private PostResponseDTO mapperToPostDTO(Post entity){
       PostResponseDTO dto= new PostResponseDTO();
       dto.setId(entity.getId());
       dto.setContent(entity.getContent());
       dto.setTitle(entity.getTitle());
       dto.setMaximumOfComments(entity.getMaximumOfComments());
       if (entity.getComments()!=null && entity.getComments().size()>0){
           dto.setComments(entity.getComments().stream().map(c->mapperToCommentDTO(c)).collect(Collectors.toSet()));
       }
       return dto;
    }
}
