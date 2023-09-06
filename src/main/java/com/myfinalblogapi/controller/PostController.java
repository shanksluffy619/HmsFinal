package com.myfinalblogapi.controller;

import com.myfinalblogapi.payload.PostDto;
import com.myfinalblogapi.payload.PostRespone;
import com.myfinalblogapi.service.PostService;
import com.myfinalblogapi.utils.AppsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createPost( @Valid @RequestBody PostDto postDto, BindingResult result) {

        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
        }else {
            PostDto post = postService.createPost(postDto);
            ResponseEntity response = new ResponseEntity<>(post, HttpStatus.CREATED);


            return response;

        }



    }


    @GetMapping("/getAllposts")
    public PostRespone getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppsConstant.Default_Page_Number, required = false)
                                    int pageNumber,

                                  @RequestParam(value = "pageSize", defaultValue = AppsConstant.Default_Page_Size, required = false) int pageSize,
                                  @RequestParam(value = "sortby", defaultValue = AppsConstant.Default_Sort_By, required = false) String sortBy,

                                  @RequestParam(value = "sortDir",defaultValue = AppsConstant.Default_Sort_Dir,required = false)String sortDir


                                    ) {
        PostRespone allPost=postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

        return allPost;
    }

    @GetMapping("/getPostBy/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable(value = "id") long id) {
        PostDto postById = postService.getPostById(id);
        ResponseEntity response = new ResponseEntity(postById, HttpStatus.OK);

        return response;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatedPost(@RequestBody PostDto postDto, @PathVariable(value = "id") Long id) {
        PostDto postDto1 = postService.updatePostById(postDto, id);
        ResponseEntity response = new ResponseEntity<>(postDto1, HttpStatus.OK);

        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> SoftDeletePostById(@PathVariable(value = "id") Long id) {
        postService.deletePostByid(id);
        ResponseEntity response = new ResponseEntity("POST DELETED SUCCESSFULLY", HttpStatus.OK);

        return response;
    }


}
