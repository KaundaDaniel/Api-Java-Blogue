package com.restapai.restApi.controller;


import com.restapai.restApi.service.PostService;
import com.restapai.restApi.utils.request.PostDTO;
import com.restapai.restApi.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll(){
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<PostResponseDTO> create(@RequestBody PostDTO dto){
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

}
