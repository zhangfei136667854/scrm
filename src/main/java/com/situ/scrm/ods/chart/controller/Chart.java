package com.situ.scrm.ods.chart.controller;

import java.io.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/chart")
public class Chart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CHART_INDEX ="ods/chart/chart_index";
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(CHART_INDEX);
		return modelAndView ;
	}

}
