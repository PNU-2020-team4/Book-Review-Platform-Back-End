package com.team4.bookreview.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.bookreview.daoImpl.BookDAOImpl;
import com.team4.bookreview.vo.BookVO;

@Controller
public class BookTestController {
	@Autowired
	private BookDAOImpl b;
	
	@RequestMapping(value="/bookTest")
	public void showBooks() {
		List<BookVO> result = b.selectAll();
		System.out.println("------ showBook ------");
		for(BookVO re : result) {
			System.out.println(re);
		}
	}
}
