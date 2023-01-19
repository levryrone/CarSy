package com.carsy.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
