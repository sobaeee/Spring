package com.kb.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //junit 버전이 4.12여야함. + lib에 spring-test 추가해야함
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//↑이걸로 끌고온다.
@Log4j //이게 있어야 log.info 사용 가능.
public class DataSourceTests {
	
	@Setter(onMethod_ = {@Autowired})  //setter가 만들어진다. 
	private DataSource dataSource;
	
	@Setter(onMethod_ = {@Autowired})
	private SqlSessionFactory SqlSessionFactory;
	
	@Test
	public void testMyBatis() {
		SqlSession sqlSession= SqlSessionFactory.openSession();
		Connection conn = sqlSession.getConnection();
		log.info(conn); //소문자 l 
	}
	
	//위에 @Test 라고 적혀 있는 것만 test가 된다.
	//@Test
	public void testConnection() {
		try {
			
			Connection conn = dataSource.getConnection();
			log.info(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
