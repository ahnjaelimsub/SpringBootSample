package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import groovy.transform.ToString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "write_free")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Board extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wr_id;

	@Column(length = 500, nullable = false)
	private	String wr_subject;
	
	@Column(length = 2000, nullable = false)
	private	String wr_content;
	
	@Column(length = 50, nullable = false)
	private	String wr_name;

	@Column(length = 50, nullable = false)
	private	String wr_password;
	
	public void change(String wr_subject, String wr_content) {
		this.wr_subject = wr_subject;
		this.wr_content = wr_content;
		
	}
}
