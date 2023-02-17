package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.Board;

@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	// @Test
	public void testInsert() {
		IntStream.rangeClosed(1,100).forEach(i -> {
			Board write_free = Board.builder()
					.wr_subject("title..." +i)
					.wr_content("content..." +i)
					.wr_name("user"+(i%10))
					.wr_password("0000")
					.build();
			Board result = boardRepository.save(write_free);
			System.out.println("wr_id : "+result.getWrId());			
		});
	}
	
	// @Test
	public void testSelect() {
		Long wr_id = 3400L;
		Optional<Board> result = boardRepository.findById(wr_id);
		Board board = result.orElseThrow();
		System.out.println(board);
	}
	
	// @Test
	public void testUpdate() {
		Long wr_id = 3400L;
		Optional<Board> result = boardRepository.findById(wr_id);
		Board board = result.orElseThrow();
		board.change("update...title 3400", "update content 3400");
		boardRepository.save(board);
	}
	
	// @Test
	public void testDelete() {
		Long wr_id = 3444L;
		boardRepository.deleteById(wr_id);
	}
	
	@Test
	public void testPaging() {
		System.out.println("???");
		Pageable pageable = PageRequest.of(0, 10);
		Page<Board> result = boardRepository.findAll(pageable);
		System.out.println("total count : "+result.getTotalElements());
		System.out.println("total pages : "+result.getTotalPages());
		System.out.println("page number : "+result.getNumber());
		System.out.println("page size : "+result.getSize());
		List<Board> boardlist = result.getContent();
		
		
	}
	
	// @Test
	public void testSearch1() {
		System.out.println("...?");
		Pageable pageable = PageRequest.of(1, 10, Sort.by("wrId").descending());
		boardRepository.search1(pageable);
	}
	
	// @Test
	public void testSearchAll() {
		System.out.println("...???");
		String[] types = {"t","c","w"};
		String keyword = "1";
		Pageable pageable = PageRequest.of(0, 10, Sort.by("wrId").descending());
		Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
		System.out.println(result.getSize());
		System.out.println(result.hasPrevious()+" : "+result.hasNext());
		result.getContent().forEach(board -> System.out.println(board.getWr_subject()));
	}

}
