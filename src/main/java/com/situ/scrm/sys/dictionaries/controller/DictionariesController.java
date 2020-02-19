package com.situ.scrm.sys.dictionaries.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionaries.domain.Dictionaries;
import com.situ.scrm.sys.dictionaries.service.DictionariesService;



@RestController
@RequestMapping("/dictionaries")
public class DictionariesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String  PAGE_DIC_INDEX = "sys/dictionaries/dictionaries_index";
	private static final String  PAGE_DIC_ADD = "sys/dictionaries/dictionaries_add";
	@Autowired
	private   DictionariesService dictionariesService;
	        
	
	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_DIC_INDEX);
		return modelAndView;
	}
	
	/**
	 * @Title: /form
	 * @Description:(进新增或修改页面)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView goAddOrEdit(ModelAndView modelAndView) {
		modelAndView.addObject("parentKey", Dictionaries.DEFAULT_PARENT_KEY);
		modelAndView.setViewName(PAGE_DIC_ADD);
		return modelAndView;
	}
	
	
	
	/**
	 * @Title: checkDicValue
	 * @Description:(检测角色名称的唯一)
	 * @param dicValue
	 * @return
	 */
	@GetMapping("/checkname")
	public Integer checkDicValue(String dicValue) {
		return  dictionariesService.checkDicValue(dicValue);
	}

	
	
	
	/**
	 * @Title: doAddDic
	 * @Description:(执行新增功能)
	 * @param role
	 * @return
	 */
	@PostMapping
	public Long doAddDic(Dictionaries  dictionaries) {
		return dictionariesService.saveDic(dictionaries);
	}

	/**
	 * @Title: findAllDictionary
	 * @Description:(查询出所有的资源信息)
	 * @return
	 */
	@GetMapping("/list")
	public LayResult findAllDictionaries() {
		return dictionariesService.findAllDictionaries();
	}

	/**
	 * @Title: goAddChildForm
	 * @Description:(进新增子字典)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/child/{parentId}")
	public ModelAndView goAddChildForm(@PathVariable Long parentId, ModelAndView modelAndView) {
		// 默认的父类CODE
		Dictionaries parentDictionaries = dictionariesService.getDictionariesById(parentId);
		modelAndView.addObject("parentKey", parentDictionaries.getDicKey());
		
		modelAndView.addObject("parentName", parentDictionaries.getDicValue());
		modelAndView.setViewName(PAGE_DIC_ADD);
		return modelAndView;
	}
	
	
	
	
	
	
}
