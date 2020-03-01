package com.situ.scrm.ods.contract.controller;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.ods.contract.domain.Contract;
import com.situ.scrm.ods.contract.service.ContractService;
import com.situ.scrm.sys.dictionary.service.DictionaryService;
import com.situ.scrm.sys.sysconfig.service.SysConfigService;

@RestController
@RequestMapping("/contract")
public class ContractController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CONTRACT_FORM_INDEX = "ods/contract/contract_add_edit";
	private static final String CONTRACT_INDEX ="ods/contract/contract_index";
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private SysConfigService sysConfigService ;
@Autowired
private ContractService contractService ;
	@GetMapping("/form")
	public ModelAndView doFormIndex(ModelAndView modelAndView) {
		modelAndView.addObject("nextContDate"+sysConfigService.getNextContDate());
		modelAndView.setViewName(CONTRACT_FORM_INDEX);
		modelAndView.addObject("dictionaryList",dictionaryService.useDictionaryList());
		return modelAndView;

	}
	@PostMapping
	public Long  doPost(Contract cont,HttpSession session) {
		return contractService.doPost(cont,session);
	}
	@GetMapping("/{page}/{limit}")
	public LayResult doFind(@PathVariable Integer page,@PathVariable Integer limit,Contract cont,HttpSession session) {
		return contractService.doFind(page,limit,cont,session);
	}
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(CONTRACT_INDEX);
		return modelAndView;
	}
}
