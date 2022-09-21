package ORG.STDCD.BOARD.repository;

import ORG.STDCD.BOARD.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
