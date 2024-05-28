package com.friends.sharing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long book_id;
    private String author;
    private String title;
    private Long holder_id;
    private Long owner_id;
}
