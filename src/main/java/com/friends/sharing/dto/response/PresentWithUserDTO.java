package com.friends.sharing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PresentWithUserDTO {
    private String box_color;
    private String content;
    private UserDTO person;
}
