package com.nike.productservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryRes {
    Long id;
    String name;
    String slug;
    String description;
    String parentName;
}
