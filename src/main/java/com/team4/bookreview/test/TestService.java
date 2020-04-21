package com.team4.bookreview.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService implements TestMapper{
	@Autowired
	private TestMapper mapper;
	
	public String selectNow() {
		return mapper.selectNow();
	}
	
	public String[] showTable() {
		return mapper.showTable();
	}
}
