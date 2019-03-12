package com.example.dao;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 엔티티 클래스 설계하는 방식
 * JPA는 자동으로 테이블을 생성할 수 있는 기능을 가지고 있으므로
 * 	1) SQL을 이용해서 테이블을 먼저 생성하고 엔티티 클래스를 만드는 방식
 * 	2) JPA를 이용해서 클래스만 설계하고 자동으로 테이블을 생성하는 방식
 * 위의 방식 모두를 사용할 수 있다.
 * 
 * 밑의 방식은 DB 테이블의 구조와 동일하게 클래스를 설계하였다.
 * 
 * 엔티티 클래스를 설계하기 위해서는 반드시 클래스 선언부에 @Entity가 설정되어야 한다.
 * @Table을 설정하는 경우에는 기본적으로 데이터베이스에 클래스명과 동일한 이름으로 생성된다.
 * 만약 클래스명과 다른 이름으로 테이블 이름을 지정하고 싶을 때 사용하면된다.
 * 
 * @Id는 가장 중요한 어노테이션으로 지정한 해당 칼럼이 식별키(PK)라는 것을 의미한다.
 * 모든 엔티티 클래스에는 @Id를 지정해 줘야 한다.
 * @Id는 주로 @GeneratedValue 어노테이션과 같이 이용해서 식별키를 어떤 전략으로 생성하는지를 명시한다.
 * 
 * @GeneratedValue는 strategy 속성과 generator 속성으로 구분된다.
 * 	strategy - AUTO, TABLE, SEQUENCE, IDENTITY
 * 	generator - @TableGenerator, @SequenceGenerator
 * */

@Entity
@Table(name = "tbl1_boards")
@Getter
@Setter
@ToString
public class Board {
	
	@Id
	// 오라클의 시퀀스를 생성하려면 @SequenceGenerator를 이용해서 생성되는 Sequence의 이름과 SequenceGenerator의 이름을 지정해 주어야 한다.
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_boards")
	@SequenceGenerator(name = "seq_boards", sequenceName = "SEQ_BOARDS", allocationSize = 1, initialValue = 1)
	public Long bno;
	public String title;
	public String writer;
	public String content;
	
	@CreationTimestamp
	public Timestamp regdate;
	
	@UpdateTimestamp
	public Timestamp updatedate;
	
}
