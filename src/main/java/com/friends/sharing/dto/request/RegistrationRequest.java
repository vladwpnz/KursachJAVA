package com.friends.sharing.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {
    @Schema(example = "vlad")
    @NotBlank(message = "Write down your name!")
    private String name;
    @Schema(example = "email@gmail.com")
    @NotBlank(message = "Write down your email!")
    //@Email // for email validation
    private String email;
    @Schema(example = "center")
    @NotBlank(message = "Write down your password!")
    private String password;
    @Schema(example = "user")
    @NotBlank(message = "Write down your authority!")
    private String authority;
}
