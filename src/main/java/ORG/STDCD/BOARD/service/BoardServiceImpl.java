package ORG.STDCD.BOARD.service;


import ORG.STDCD.BOARD.dto.BoardDTO;
import ORG.STDCD.BOARD.entity.Board;
import ORG.STDCD.BOARD.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO dto) {
        Board board = dtoToEntity(dto);
        boardRepository.save(board);

        return board.getBno();
    }
}
