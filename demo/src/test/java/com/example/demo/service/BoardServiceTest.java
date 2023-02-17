package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	private BoardService boardService;
	
	// @Test
	public void testRegister() {
		System.out.println(boardService.getClass().getName());
		BoardDTO boardDTO = BoardDTO.builder()
				.wr_subject("Spring Boot Board Test...")
				.wr_content("test")
				.wr_name("user00")
				.wr_password("0000")
				.build();
		Long wr_id = boardService.register(boardDTO);
		System.out.println("wr_id : "+wr_id);
	}
	
	// @Test
	public void testModify() {
		BoardDTO boardDTO = BoardDTO.builder()
				.wrId(3440L)
				.wr_subject("Updated...3340")
				.wr_content("Updated....3440")
				.build();
		boardService.modify(boardDTO);
	}
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.type("tcw")
				.keyword("1")
				.page(1)
				.size(10)
				.build();
		PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
		System.out.println(responseDTO);
	}
}
