package com.example.demo.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.demo.domain.Board;
import com.example.demo.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

	public BoardSearchImpl() {
		super(Board.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<Board> search1(Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.or(board.wr_subject.contains("1"));
		
		query.where(booleanBuilder);
		query.where(board.wr_id.gt(0L));
		
		// ∆‰¿Ã¬°
		// this.getQuerydsl().applyPagination(pageable, query);
		
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		return null;
	}

	@Override
	public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		if((types != null && types.length >0) && keyword !=null) {
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			for(String type :  types) {
				switch(type) {
				case "t" :
					booleanBuilder.or(board.wr_subject.contains(keyword));
					break;
				case "c" :
					booleanBuilder.or(board.wr_content.contains(keyword));
					break;
				case "w" :
					booleanBuilder.or(board.wr_name.contains(keyword));
					break;					
				}
			} // end of for
			query.where(booleanBuilder);
		} // end of if 
		
		query.where(board.wr_id.gt(0L));
		
		// paging
		// this.getQuerydsl().applyPagination(pageable, query);
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		
		return new PageImpl<>(list, pageable, count);
	}



}
