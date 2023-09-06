package com.myfinalblogapi.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommentDto {


    private long id;

    private String body;


    private String email;

    private String name;
}
