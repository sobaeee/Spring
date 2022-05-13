package com.kb.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kb.domain.SampleDTO;
import com.kb.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/sample/*")
//url 주소 : 도메인/sample/ | *<- sample이란 폴더 속에 들어있다.
@Controller
public class SampleController {
	
	@RequestMapping("/")
	public void basic() {
		log.info("-----------------------");
	}
	
	@GetMapping("basicOnlyGet") //get방식만 처리하겠다
	public void basicGet() {
		log.info("-----------------------GET");
		
		//리턴 값이 void. [/WEB-INF/jsp/sample/basicOnlyGet.jsp] 그냥 주소에 대한 jsp파일을 찾는다. 디렉토리는 sample이 된다.
	}
	
	/*
	 * @PostMapping("basicOnlyPost") //post방식만 처리하겠다 public void basicPost() {
	 * log.info("-----------------------POST"); }
	 */
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01"; //처음엔 빈값이 나오지만 상단에 ?name=키보드&age=10 을 붙여 값을 던져주면 console 창에 값이 뜬다.
		//리턴값이 String. [/WEB-INF/jsp/ex01.jsp]  String과 void의 차이로는 return 값이 있고 없고도 있음.
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info(name);
		log.info(age);
		return "ex02";
	} //name2나 age2로 상단에 치면 이름이 달라서 값이 안넘어감.
	
	@GetMapping("/ex03")
	public String ex03(@RequestParam("name") ArrayList<String> names) {
		log.info(names);
		return "ex03"; //리턴은 페이지를 보여주는 것. 현재는 페이지 설정해둔 것이 없어서 숫자가 달라도 ㄱㅊ. 페이지 생성시 꼭 맞는 이름으로 입력할 것.
	}
	
	@InitBinder //날짜전용 형변환 @
	public void InitBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, 
				new CustomDateEditor(dateFormat, false)); 
		// false의 자리 위치의 의미 : allowEmpty - if empty strings should be allowed(빈 문자열을 허용해야 하는 경우)
		//true일땐 date값 안넣으면 null로 처리. title값은 나옴. false일땐 date값 안넣으면 오류400 뜨고 값이 하나도 안나옴.
	}
	
	@GetMapping("/ex04")
	public String ex04(TodoDTO todo) {
		log.info(todo);
		return "ex04";
	}
	
	@GetMapping("/ex05")
	public String ex05(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info(dto);
		log.info(page);
		return "sample/ex05";
	}  //ModelAttribute : 값을 리턴하겠다.
	//http://localhost/sample/ex05?name=하이&page=1
	
	@GetMapping("/ex06")
	public String ex06(String name, int age, RedirectAttributes rttr) {
		rttr.addFlashAttribute("name", name );
		rttr.addFlashAttribute("age", age );
		//값 설정해둔 것을 넘겨버려라.
		return "redirect:/";
		//http://localhost/sample/ex06?name=하이&age=1
	}
	
	@GetMapping("/ex07")
	public String ex07(RedirectAttributes rttr) {
		
		
		rttr.addFlashAttribute("name", "초코" );
		rttr.addFlashAttribute("age", 20 );
		//안에서 값을 줘서 넘겨버려라.
		return "redirect:/";
		//http://localhost/sample/ex07
		//home.jsp에 ${age} 을 넣자 값이 get방식으로 넘어간 것은 확인이 되었다.
		//한글이 깨져서 일단 나이만 넣어둠.
	}
	
	@GetMapping("/ex08")
	public @ResponseBody SampleDTO ex08() {
		log.info("/ex08.............");
		SampleDTO dto = new SampleDTO();
		dto.setName("아하");
		dto.setAge(10);
		
		return dto;
	}
	
	@GetMapping("/ex09")
	public ResponseEntity<String> ex09() {
		String msg = "{\"name\":\"아하\",\"age\":10}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("content-type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
	
	
}
