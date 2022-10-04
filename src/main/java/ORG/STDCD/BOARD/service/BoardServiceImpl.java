package ORG.STDCD.BOARD.service;


import ORG.STDCD.BOARD.dto.BoardDTO;
import ORG.STDCD.BOARD.dto.PageRequestDTO;
import ORG.STDCD.BOARD.dto.PageResultDTO;
import ORG.STDCD.BOARD.entity.Board;
import ORG.STDCD.BOARD.entity.Member;
import ORG.STDCD.BOARD.repository.BoardRepository;
import ORG.STDCD.BOARD.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {
        Board board = dtoToEntity(dto);
        boardRepository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO){

        Function<Object[], BoardDTO> fn = (en -> entityToDTO( (Board)en[0], (Member)en[1], (Long)en[2]) );
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno){
        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[])result;
        return entityToDTO( (Board)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno){
        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);
    }

    @Override
    public void modify(BoardDTO boardDTO){
        Board board = boardRepository.getOne(boardDTO.getBno());
        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());
        boardRepository.save(board);
    }

}
