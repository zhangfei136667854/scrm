package com.situ.scrm.ods.chart.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.situ.scrm.ods.chart.domain.Data;

public interface ChartService {

	List<Data> getType(HttpSession session);

	List<Data> getFrom(HttpSession session);

}
