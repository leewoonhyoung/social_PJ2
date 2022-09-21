package ORG.STDCD.BOARD.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name ="tbl_member")
public class Member extends BaseEntity{

    @Id @GeneratedValue
    private String email;

    private String password;

    private String name;

}
