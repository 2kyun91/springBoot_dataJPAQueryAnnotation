package com.example.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.dao.Board;
/*
 * Predicate는 '이 조건이 맞다'고 판단하는 근거를 함수로 제공하는것이다.
 * Repository에서 Predicate를 파라미터로 전달하기 위해서는 QuerydslPredicateExecutor<T> 인터페이스를 상속받는다.
 * 리턴타입은 boolean으로 주로 BooleanBuilder를 이용해서 생성한다.
 * */
public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	
	/*
	 * @Query에는 JPQL을 이용하는데 JPQL은 JPA에서 사용하는 쿼리언어이다.
	 * SQL과 유사한 구문들로 구성되고 JPA의 구현체(기동 메소드)에서 이를 해석해서 실행한다.
	 * [%?1%]은 JDBC의 PreparedStatement에서 사용한 것과 동일하다고 생각하면 된다, '?1'은 첫 번째로 전달되는 파라미터로 생각하면 된다.
	 * JPQL 방식으로 사용하지 않고 데이터베이스에 맞는 쿼리문을 사용하려면 nativeQuery = true 속성을 지정해주면 된다.
	 * */
	@Query(value = "select * from tbl1_boards b where b.title like %?1% and b.bno > 0 order by b.bno desc", nativeQuery = true)
	public List<Board> findByTitle(String title);
	
	/*
	 * [%:content%]와 같이 쿼리를 처리할 때는 @Param("content")로 파라미터 명을 지정할 수 있다.
	 * 여러 개의 파라미터를 전달할 때 파라미터 명을 이용해 쉽게 구분해서 전달할 수 있다.
	 * */
	@Query(value = "select * from tbl1_boards b where b.content like %:content% and b.bno > 0 order by b.bno desc", nativeQuery = true)
	public List<Board> findByContent(@Param("content") String content);
	
	/*
	 * #{#entityName}은 Repository 인터페이스를 정의할때 <엔티티 타입(Board), PK 타입>에서 엔티티 타입을 자동으로 참고한다.
	 * 유사한 상속 구조의 Repository 인터페이스를 여러 개 생성하는 경우에 유용하게 사용할 수 있다.
	 * 엔티티 타입의 이름과 실제 데이터베이스 상 테이블 명이 같아야 작동한다.
	 * */
	@Query(value = "select * from #{#entityName} b where b.writer like %?1% and b.bno > 0 order by b.bno desc", nativeQuery = true)
	public List<Board> findByWriter(String writer);
	
	/*
	 * 필요한 칼럼을 지정하는 경우 리턴 타입이 엔티티 타입(Board)가 아니라 Object[]의 리스트 형태로 해야한다.
	 * */
	@Query(value = "select b.bno, b.title, b.writer, b.regdate from tbl1_boards b where b.title like %?1% and b.bno > 0 order by b.bno desc", nativeQuery = true)
	public List<Object []> findByTitle2(String title);
	
	/*
	 * @Query를 이용하더라도 페이징 처리를 하는 Pageable 인터페이스도 활용이 가능하다.
	 * */
	@Query(value = "select * from tbl1_boards b where b.bno > 0 order by b.bno desc", nativeQuery = true)
	public List<Board> findByPage(Pageable pageable);
	
}
