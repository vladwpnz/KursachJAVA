package com.friends.sharing.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiveBookRequest {
    @Schema(example = "Effective Java")
    @NotBlank(message = "Write down the title of your book!")
    private String title;
    @Schema(example = "email@gmail.com")
    @NotBlank(message = "Write down the username of the person to who you want to give your book!")
    private String username;
}
