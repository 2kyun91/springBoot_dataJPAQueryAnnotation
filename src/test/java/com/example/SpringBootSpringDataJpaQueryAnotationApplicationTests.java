package com.example;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.Board;
import com.example.dao.QBoard;
import com.example.persistence.BoardRepository;
import com.querydsl.core.BooleanBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSpringDataJpaQueryAnotationApplicationTests {

	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testByTitle() {
		boardRepo.findByTitle("17").forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByContent() {
		boardRepo.findByContent("15번").forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByWriter() {
		boardRepo.findByWriter("user02").forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitle2() { // 필요한 칼럼만 조회하는 경우
		boardRepo.findByTitle2("123번").forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
	
	@Test
	public void testByPaging() {
		boardRepo.findByPage(PageRequest.of(0, 10)).forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testPredicate() {
		String type = "t";
		String Keyword = "17";
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		QBoard board = QBoard.board;
		
		if (type.equals("t")) {
			booleanBuilder.and(board.title.like("%" + Keyword + "%"));
		}
		
		booleanBuilder.and(board.bno.gt(0L));
		
		Page<Board> result = boardRepo.findAll(booleanBuilder, PageRequest.of(0, 10));
		
		System.out.println("페이지 크기 : " + result.getSize());
		System.out.println("총 페이지 : " + result.getTotalPages());
		System.out.println("총 개수 : " + result.getTotalElements());
		System.out.println("다음페이지 : " + result.nextPageable());
		
		List<Board> list = result.getContent();
		
		list.forEach(b -> System.out.println(b));
	}
}
