package com.kb.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//위에 두 가지가 있어야 테스트 실행이 된다. 아니면 빨간불이 뜬다.
@Log4j
public class TimeMapperTests {
	
	@Setter(onMethod_ = @Autowired) //옛날 버전에는 onMethod에 _가 안들어갔지만 5.7부터는 _가 추가가 된다.
	private TimeMapper timeMapper;
	
	//@Test
	public void testGetTime() {
		log.info(timeMapper.getTime()); //내용 출력
	}
	
	@Test
	public void testGetTime2() {
		log.info(timeMapper.getTime2());
	}
}
