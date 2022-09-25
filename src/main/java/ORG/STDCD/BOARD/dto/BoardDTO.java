package ORG.STDCD.BOARD.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;

    private String title;

    private String content;
}
