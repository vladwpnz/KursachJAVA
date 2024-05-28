package com.friends.sharing.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPresentRequest {
    @Schema(example = "red")
    @NotBlank(message = "Describe the present!")
    private String box_color;
    @Schema(example = "Joshua Bloch")
    @NotBlank(message = "Describe the content of the present!")
    private String content;
}
