package com.acorn.exbatis;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;


@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/test1.xml" , "file:src/main/webapp/WEB-INF/spring/**/test2.xml"} )
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j                                                                                                                                               
public class userRepositoryTest {

	@Autowired
	userRepository rep;
	
	@Test
	public void test() throws Exception {
		int result  = rep.selectAll().size();
		System.out.println(  result);
	}
}
