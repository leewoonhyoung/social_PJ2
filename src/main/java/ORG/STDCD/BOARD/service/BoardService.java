package ORG.STDCD.BOARD.service;

import ORG.STDCD.BOARD.dto.BoardDTO;
import ORG.STDCD.BOARD.entity.Board;
import ORG.STDCD.BOARD.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto){
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }
}
