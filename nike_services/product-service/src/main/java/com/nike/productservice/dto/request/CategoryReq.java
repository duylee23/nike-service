package com.nike.productservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryReq {

    @Size(min = 8, message = "Name must be at least 4 characters")
    String name;
    String slug;
    String description;
    String parentName;
}
