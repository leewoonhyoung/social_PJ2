package ORG.STDCD.BOARD.service;

import ORG.STDCD.BOARD.dto.BoardDTO;
import ORG.STDCD.BOARD.dto.PageRequestDTO;
import ORG.STDCD.BOARD.dto.PageResultDTO;
import ORG.STDCD.BOARD.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){

        BoardDTO dto = BoardDTO.builder()
                .title("Test.")
                .content("Test..")
                .writerEmail("user55@aaa.com")
                .build();

        Long bno = boardService.register(dto);
    }

    @Test
    @DisplayName("getList TEST")
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<BoardDTO,Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO : result.getDtoList()){
            System.out.println("boardDTO = " + boardDTO);
        }

    }

    @Test
    @DisplayName("BoardService get Test")
    public void testGet(){

        Long bno = 100L;
        BoardDTO boardDTO =  boardService.get(bno);
        System.out.println("boardDTO = " + boardDTO);
    }

    @Test
    @DisplayName("삭제 테스트 데이터 추가")
    public void testRemove(){
         Long bno = 1L;
         boardService.removeWithReplies(bno);
    }


    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목 변경합니다")
                .content("내용 변경합니다")
                .build();

        boardService.modify(boardDTO);

    }

}