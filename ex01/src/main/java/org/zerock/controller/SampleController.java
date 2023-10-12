package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@RequestMapping("")
	public void basic() {
		log.info("basic.............");
	}
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get....");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic getr only get......");
	}
	
	@GetMapping("/ex01")	// sample/ex01?name=어쩌구&age=저쩌구
	public String ex01(SampleDTO dto) {
		log.info(""+dto);	// name : 어쩌구, age : 저쩌구  출력
		
		return "ex01";	
	}
	
	@GetMapping("/ex02")// 파라미터로사용된 변수의 이름과 전달되는 파라미터의 이름이 다른 경우 사용
						//  /sample/ex02?name=어쩌구&age=저쩌구
	public String ex02(@RequestParam("name")String naming,@RequestParam("age") int age) {
		log.info("name : "+naming);
		log.info("age : "+age);
		
		return "ex02";
	}
	
	@GetMapping("/ex02List")				// ids로 여러번 입력 가능하도록 ArrayList 사용(배열도 동일)
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		
		log.info("ids : "+ids);
		
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids")String[] ids) {
		
		log.info("array ids : "+Arrays.toString(ids));
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean")	// sample/ex02Bean?list[0].name=aaa&lis[1].name=bbb처럼 []형식으로 가능
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos : "+list);
			
		return "ex02Bean";
	}
	
	// @InitBinder TodoDTO에서 @DateTimeFormat사용하면 생략가능
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(
				java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo : "+todo);
		
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {	// 기본형타입을 강제로 모델에 주입
		log.info("dto : "+dto);
		log.info("page : "+page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex05")
	public void ex05() {	// void라서 return없고 ex05파일 찾아감 =>현재는 ex05.jsp가 없으므로 404
		log.info("/ex05.....");
	}
	
	@GetMapping("/ex06")	// dto 반환해야하므로 @Responsebody를 SampleDTO앞에 달아줌
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06.......");
		SampleDTO dto = new SampleDTO();	// 객체 타입 => pom.xml에 jackson-databind를 추가해줌
		dto.setAge(10);
		dto.setName("taemin");
		
		return dto;		// json타입으로 객체 변환해서 반환
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07......");
		
		// {"name": "홍길동"}
		String msg = "{\"name\":\"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
				// HttpServletRequest,Response대신 ResponseEntity 사용
		return new ResponseEntity<>(msg, header, HttpStatus.OK);	// name : 홍길동, 200 OK 반환
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload..........");
	}
	
	@PostMapping("/exUploadPost")
	public void ExUploadPost(ArrayList<MultipartFile> files) {
							// 전달되는 파라미터가 동일한 이름으로 여러개 존재하면 배열로 처리가능 => 파라미터를 MultipartFile로 작성
		files.forEach(file->{
			log.info("-------------------------");
			log.info("name : "+file.getOriginalFilename());
			log.info("size : "+file.getSize());
		});
		
	}
}
