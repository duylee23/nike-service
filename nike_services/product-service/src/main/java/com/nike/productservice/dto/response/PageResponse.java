package com.nike.productservice.dto.response;

import com.nike.productservice.dto.ProductDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> items;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private int totalElements;
}
