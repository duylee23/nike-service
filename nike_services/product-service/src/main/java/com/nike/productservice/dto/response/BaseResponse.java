package com.nike.productservice.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private boolean success;
    private String message;
    private T data;
}
