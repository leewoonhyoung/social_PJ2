package ORG.STDCD.BOARD.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
@Table(name ="tbl_board")
public class Board extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String text;

    private String content;

    @ManyToOne
    private Member writer;


}
