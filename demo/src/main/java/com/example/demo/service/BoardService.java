package com.example.demo.service;

import com.example.demo.dto.BoardDTO;

public interface BoardService {
	Long register(BoardDTO boardDTO);
	BoardDTO readOne(Long wr_id);
	void modify(BoardDTO boardDTO);
	void remove(Long wr_id);
}
