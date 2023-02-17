package com.example.demo.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Board;
import com.example.demo.dto.BoardDTO;
import com.example.demo.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

	private final ModelMapper modelMapper;
	private final BoardRepository boardRepository;
	
	@Override
	public Long register(BoardDTO boardDTO) {
		Board board = modelMapper.map(boardDTO, Board.class);
		Long wr_id = boardRepository.save(board).getWr_id();
		return wr_id;
	}

	@Override
	public BoardDTO readOne(Long wr_id) {
		Optional<Board> result = boardRepository.findById(wr_id);
		Board board = result.orElseThrow();
		BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
		return boardDTO;
	}

	@Override
	public void modify(BoardDTO boardDTO) {
		Optional<Board> result = boardRepository.findById(boardDTO.getWr_id());
		Board board = result.orElseThrow();
		board.change(boardDTO.getWr_subject(), boardDTO.getWr_content());
		boardRepository.save(board);
	}

	@Override
	public void remove(Long wr_id) {
		boardRepository.deleteById(wr_id);
		
	}

}
