package com.situ.scrm.ods.chart.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.situ.scrm.ods.contract.dao.ContractDao;

public class ChartService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ContractDao contractDao ;
	
	

}
