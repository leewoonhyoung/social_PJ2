package ORG.STDCD.BOARD.repository;

import ORG.STDCD.BOARD.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.StoredProcedureParameter;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query( "select b,w " +
            "from Board b left join b.writer w " +
            "where b.bno=:bno")
    Object getBoardWithWriter(@Param( "bno" ) Long bno );

    @Query( "select b, r  " +
            "from Board b left  join Reply r " +
            "on r.board = b " +
            "where b.bno= :bno")
    List<Object[]> getBoardWithReply(@Param( "bno" ) Long bno );

    @Query( value = " SELECT b, w, count(r) " +
            " FROM Board b " +
            " LEFT JOIN b.writer w " +
            " LEFT JOIN Reply r ON r.board = b " +
            "  GROUP BY b ",
            countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);


    @Query( "SELECT b, w, count(r) " +
            "FROM Board  b " +
            "LEFT JOIN b.writer w " +
            "LEFT OUTER JOIN Reply r " +
            "ON r.board = b " +
            "WHERE b.bno = :bno")
            Object getBoardByBno(@Param( "bno" ) Long bno);

    @Modifying
    @Query( "delete from Reply r where r.board.bno = :bno " )
    public void deleteByBno(Long bno);
}
