package com.friends.sharing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookWithUserDTO {
    private String author;
    private String title;
    private UserDTO person;
}
