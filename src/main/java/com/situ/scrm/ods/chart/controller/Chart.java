package com.situ.scrm.ods.chart.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.ods.chart.domain.Data;
import com.situ.scrm.ods.chart.service.ChartService;
@RestController
@RequestMapping("/chart")
public class Chart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ChartService chartService ;
	private static final String CHART_INDEX ="ods/chart/chart_index";
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(CHART_INDEX);
		return modelAndView ;
	}
	@GetMapping("/contType")
public List<Data> doFindType(HttpSession session){
	return  chartService.getType(session);
}
	@GetMapping("/cusForm")
	public List<Data> doFindFrom(HttpSession session){
		return chartService.getFrom(session);
	}
}
