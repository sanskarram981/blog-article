package com.blogarticle.app.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PaginatedPostResponse {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private int totalElement;
    private boolean lastPage;
    private List<PostDto> data;
}
