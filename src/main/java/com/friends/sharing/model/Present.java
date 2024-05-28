package com.friends.sharing.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "presents")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Present {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long present_id;
    private String box_color;
    private String content;
    @ManyToOne
    @JoinColumn(name = "holder_id")
    private User holder;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
