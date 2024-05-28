package com.friends.sharing.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GivePresentRequest {
    @Schema(example = "red")
    @NotBlank(message = "Describe the present you want to give!")
    private String box_color;
    @Schema(example = "email@gmail.com")
    @NotBlank(message = "Write down the username of the person to who you want to give your book!")
    private String username;
}
