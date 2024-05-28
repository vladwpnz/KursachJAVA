package com.friends.sharing.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnBookRequest {
    @Schema(example = "Effective Java")
    @NotBlank(message = "Write down the title of the book you want to return!")
    private String title;
}
