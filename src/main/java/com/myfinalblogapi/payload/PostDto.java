package com.myfinalblogapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 2,message = "Post title should be atleast 2 charachter")
    private String title;


    @NotEmpty
    private String content;

    @NotEmpty
    private String description;
}
