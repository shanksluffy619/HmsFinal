package com.myfinalblogapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRespone {

    private List<PostDto> content;

    private int pageNumber;

    private int pageSize;

    private int totalPages;
    private int totalElements;

    private boolean last;

}
