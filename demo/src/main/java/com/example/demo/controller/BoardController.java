package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
		System.out.println(responseDTO);
		model.addAttribute("responseDTO", responseDTO);
	}
	@GetMapping("/register")
	public void registerGET(Long wrId, Model model) {
		if(wrId==null) {
			log.info("<Board Controller> register GET");
		}else {
			log.info("<Board Controller> modify GET");
			BoardDTO boardDTO = boardService.readOne(wrId);
			log.info(boardDTO);
			model.addAttribute("dto", boardDTO);	
		}
	}
	@PostMapping("/modify")
	public String modifyPOST(PageRequestDTO pageRequestDTO, @Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		log.info("<Board Controller> modify POST");
		if(bindingResult.hasErrors()) {
			log.info("has errors!");
			String link = pageRequestDTO.getLink();
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			redirectAttributes.addAttribute("wrId", boardDTO.getWrId());
			return "redirect:/board/register?"+link;
		}
		boardService.modify(boardDTO);
		redirectAttributes.addAttribute("result", "modified");
		redirectAttributes.addAttribute("wrId", boardDTO.getWrId());
		return "redirect:/board/read";
	}
	@PostMapping("/register")
	public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		log.info("<Board Controller> register Post");
		if(bindingResult.hasErrors()) {
			log.info("has errors!");
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			log.info(bindingResult.getAllErrors());
			return "redirect:/board/register";
		}
		log.info(boardDTO);
		Long wrId = boardService.register(boardDTO);
		redirectAttributes.addFlashAttribute("result", wrId);
		return "redirect:/board/list";
	}
	@GetMapping("/read")
	public void read(Long wrId, PageRequestDTO pageRequestDTO, Model model) { // 변수명 대소문자 구분 주의
		BoardDTO boardDTO = boardService.readOne(wrId);
		log.info(boardDTO);
		model.addAttribute("dto", boardDTO);	
	}
	@PostMapping("/remove")
	public String remove(Long wrId, RedirectAttributes redirectAttributes) {
		log.info("remove post wrId : "+wrId);
		boardService.remove(wrId);
		redirectAttributes.addFlashAttribute("result", "removed");
		return "redirect:/board/list";
	}
}
