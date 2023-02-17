package com.example.demo.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private Long wr_id;
	private String wr_subject;
	private String wr_content;
	private String wr_name;
	private String wr_password;
	private Timestamp wr_datetime;
	private Timestamp wr_last;
}
