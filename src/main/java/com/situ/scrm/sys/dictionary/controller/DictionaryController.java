package com.situ.scrm.sys.dictionary.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionary.domain.Dictionary;
import com.situ.scrm.sys.dictionary.service.DictionaryService;
/**
 * 数据字典的Controller层
 * @author 14850
 *
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_INDEX_DICTIONARY = "sys/dictionary/dictionary_index";
	private static final String PAGE_DICTIONARY_ADD_EDIT = "sys/dictionary/dictionary_add_edit";
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_INDEX_DICTIONARY);
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
		// 默认的父类CODE
		modelAndView.addObject("parentKey", Dictionary.DEFAULT_PARENT_KEY);
		modelAndView.setViewName(PAGE_DICTIONARY_ADD_EDIT);
		return modelAndView;
	}
	/**
	 * @Title: saveSysResource
	 * @Description:(新增字典信息)
	 * @param sysResource
	 * @return
	 */
	@PostMapping
	public Long saveDictionary(Dictionary dictionary) {
		return dictionaryService.saveDictionary(dictionary);
	}
	/**
	 * @Title: findAllResource
	 * @Description:(查询出所有的资源信息)
	 * @return
	 */
	@GetMapping("/list")
	public LayResult findAllDictionary() {
		return dictionaryService.findAllDictionary();
	}
	/**
	 * @Title: goAddChildForm
	 * @Description:(进新增子资源)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/child/{parentId}")
	public ModelAndView goAddChildForm(@PathVariable Long parentId, ModelAndView modelAndView) {
		// 默认的父类CODE
		Dictionary parentDictionary = dictionaryService.getDictionaryById(parentId);
		modelAndView.addObject("parentKey", parentDictionary.getDicKey());
		modelAndView.addObject("parentName", parentDictionary.getDicCode());
		modelAndView.setViewName(PAGE_DICTIONARY_ADD_EDIT);
		return modelAndView;
	}
	/**
	 * @Title: doDeleteSysResource
	 * @Description:(删除)
	 * @param rowId
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Integer doDeleteDictionary(@PathVariable Long rowId) {
		return dictionaryService.doDeleteDictionary(rowId);
	}
	/**
	 * @Title: getResourceById
	 * @Description:(进修改)
	 * @param rowId
	 * @return
	 */
	@GetMapping("/{rowId}")
	public Dictionary getDictionaryById(@PathVariable Long rowId) {
		Dictionary dictionary = dictionaryService.getAllDictionaryById(rowId);
		return dictionary;
	}
	/**
	 * @Title: updateSySresource
	 * @Description:(修改资源)
	 * @param sysResource
	 * @return
	 */
	@PutMapping
	public Long updateDictionary(Dictionary dictionary) {
		return dictionaryService.updateDictionary(dictionary);
	}
	@GetMapping("/checkdiccode")
	public Integer checkDicCode(String dicCode, String parentKey) {
		return dictionaryService.checkDicCode(dicCode, parentKey);
	}
	
}
