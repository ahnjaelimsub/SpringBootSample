package com.example.demo.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private Long wrId;
	
	@NotEmpty
	@Size(min=2, max=200)
	private String wr_subject;
	@NotEmpty
	private String wr_content;
	@NotEmpty
	private String wr_name;
	@NotEmpty
	private String wr_password;
	private Timestamp wr_datetime;
	private Timestamp wr_last;
}
