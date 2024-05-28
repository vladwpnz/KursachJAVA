package com.friends.sharing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ItemsWithUser {
    private List<BookWithUserDTO> books;
    //private List<PresentWithUserDTO> presents;
}
