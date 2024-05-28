package com.friends.sharing.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBookRequest {
    @Schema(example = "Joshua Bloch")
    @NotBlank(message = "Write down the author of the book!")
    private String author;
    @Schema(example = "Effective Java")
    @NotBlank(message = "Write down the title of the book!")
    private String title;
}
