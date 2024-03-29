package com.restapai.restApi.controller;


import com.restapai.restApi.entity.Post;
import com.restapai.restApi.repository.PostRepository;
import com.restapai.restApi.service.PostService;
import com.restapai.restApi.utils.ValidateObject;
import com.restapai.restApi.utils.request.PostDTO;
import com.restapai.restApi.utils.response.PostResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll(){
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody PostDTO dto){
    Map <String, String> errorValidator= ValidateObject.validatePostDTO(dto);
    if (!ObjectUtils.isEmpty(errorValidator)){
        return new ResponseEntity<>(errorValidator, HttpStatus.BAD_REQUEST);

    }
        return new ResponseEntity<>(postService.save(dto),HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("idToUpdate") Long id, @RequestBody PostDTO dto){
        return  new ResponseEntity<>(postService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idToDelete}")
    public ResponseEntity<String> delete (@PathVariable("idToDelete") Long id){
        return new ResponseEntity<>(postService.delete(id), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/getAllWithPage")
    public ResponseEntity<Page<Post>> getAllWithPage(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(postRepository.findAll(pageable), HttpStatus.OK);
    }

}
