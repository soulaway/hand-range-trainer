package com.github.soulaway.ng2spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.soulaway.myRestApi.model.RangeTable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
	private static final String RANGE = "AA-44,AKo-AJo,KQo,AKs-A9s,KQs-KTs,QJs-QTs,JTs,T9s,98s,87s,76s,65s,[50]A8s-A2s,K9s,Q9s,J9s,T8s,97s[/50],[70]22[/70]";
	
	@Autowired
	TableService service;
	
	@Test
	public void contextLoads() {
		RangeTable mtx = service.generate(RANGE);
		//TODO 
	}

}
