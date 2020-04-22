package com.team4.bookreview.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.bookreview.daoImpl.ReviewDAOImpl;
import com.team4.bookreview.vo.ReviewVO;

@Controller
public class ReviewTestController {
	@Autowired
	private ReviewDAOImpl r;

	@RequestMapping(value="/reviewTest")
	public void showReview() {
		List<ReviewVO> result = r.selectAll();
		System.out.println("------ showReview ------");
		for(ReviewVO re : result) {
			System.out.println(re);
		}
	}
	
}
