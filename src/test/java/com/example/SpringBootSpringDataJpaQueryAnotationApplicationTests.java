package com.example;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSpringDataJpaQueryAnotationApplicationTests {

	@Autowired
	private BoardRepository boardRepo;
	
//	@Test
//	public void testByTitle() {
//		boardRepo.findByTitle("17").forEach(board -> System.out.println(board));
//	}
//	
//	@Test
//	public void testByContent() {
//		boardRepo.findByContent("15번").forEach(board -> System.out.println(board));
//	}
//	
//	@Test
//	public void testByWriter() {
//		boardRepo.findByWriter("user02").forEach(board -> System.out.println(board));
//	}
//	
//	@Test
//	public void testByTitle2() { // 필요한 칼럼만 조회하는 경우
//		boardRepo.findByTitle2("123번").forEach(arr -> System.out.println(Arrays.toString(arr)));
//	}
	
	@Test
	public void testByPaging() {
		boardRepo.findByPage(PageRequest.of(0, 10)).forEach(board -> System.out.println(board));
	}
	
}
