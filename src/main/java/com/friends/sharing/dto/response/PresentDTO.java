package com.friends.sharing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PresentDTO {
    private Long present_id;
    private String box_color;
    private String content;
    private Long holder_id;
    private Long owner_id;
}
