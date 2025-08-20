package com.nike.authservice.dto.response;

import lombok.*;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class IntrospectResponse {
   private boolean isValid;
}
