package com.myfinalblogapi.controller;

import com.myfinalblogapi.payload.CommentDto;
import com.myfinalblogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}")
public class CommentController {
@Autowired
private CommentService commentService;

@PostMapping("/comments")
    public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto commentDto, @PathVariable(value = "postId") long postId){

    CommentDto commentByPostId = commentService.createCommentByPostId(postId, commentDto);
ResponseEntity response = new ResponseEntity(commentByPostId, HttpStatus.CREATED);
    return response;
    }


    @GetMapping("/comments")
    public ResponseEntity<CommentDto> getAllCommentSByPost(@PathVariable(value = "postId") Long postId){

        List<CommentDto> allCommentsById = commentService.getAllCommentsById(postId);

        ResponseEntity response = new ResponseEntity<>(allCommentsById,HttpStatus.OK);
        return response;
    }

    @GetMapping("/comments/{commentId}")
public ResponseEntity<CommentDto> getCommentByPostIdByCommentId(@PathVariable(value = "postId")Long postId,
                                                                @PathVariable(value = "commentId") long commentId
                                                                ){

    CommentDto commet = commentService.getCommentByPostAndCommentId(postId, commentId);

    ResponseEntity response = new ResponseEntity(commet,HttpStatus.OK);
    return response;
}

    @PutMapping("/comments/{commentId}")
public ResponseEntity<CommentDto> updateCommentByPost(@PathVariable(value = "commentId")long commentId,@PathVariable(value = "postId")long postId,
                                                      @RequestBody CommentDto commentDto

                                                      ){

        CommentDto dto = commentService.updateCommetById(postId, commentId, commentDto);
ResponseEntity response = new ResponseEntity(dto,HttpStatus.OK);

return response;
    }
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> DeleteCommentById(@PathVariable(value = "postId") long postId,
                                                    @PathVariable(value = "commentId")long commentId

                                                    ){

        commentService.deleteComment(postId,commentId);
    ResponseEntity response = new ResponseEntity("Comment deleted Successfully",HttpStatus.OK);
    return response ;
    }

}
