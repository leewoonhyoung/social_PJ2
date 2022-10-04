package ORG.STDCD.BOARD.repository;

import ORG.STDCD.BOARD.entity.Board;
import ORG.STDCD.BOARD.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("BoardRepository Test")
    public void insertBoard(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder()
                    .email("user"+i + "@aaa.com")
                    .build();

            Board board = Board.builder()
                    .title("Title..." +i)
                    .content("Content....." + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    @DisplayName("read 테스트")
    @Transactional
    public void testRead(){
        Optional<Board> result = boardRepository.findById(100L);

        Board board =  result.get();
        System.out.println("board = " + board);
        System.out.println("writer = " + board.getWriter());

    }

    @Test
    @DisplayName("JPQL로 구현해보기")
    public void testReadWithWriter(){
        Object result = boardRepository.getBoardWithWriter(100L);

        Object[] arr = (Object[]) result;

        System.out.println("Arrays.toString(arr = " + Arrays.toString(arr));

    }
    @Test
    @DisplayName("Reply : Many, board:One")
    public void testGetBoardWithReply(){

        List<Object[]> result = boardRepository.getBoardWithReply(100L);
        
        for (Object[] arr : result){
            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("reply count")
    public void testwithReplyCount(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(i ->{
            Object[] arr = (Object[])i;
            System.out.println(Arrays.toString(i));
        });
    }


}