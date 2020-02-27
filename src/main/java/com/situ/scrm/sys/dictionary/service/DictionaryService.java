package com.situ.scrm.sys.dictionary.service;

import java.util.List;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionary.domain.Dictionary;

public interface DictionaryService {

	Long saveDictionary(Dictionary dictionary);//新增一个数据字典信息

	LayResult findAllDictionary();//查询所有的字典信息

	Dictionary getDictionaryById(Long rowId);//根据主键查询数据字典对象

	Integer doDeleteDictionary(Long rowId);//删除

	Dictionary getAllDictionaryById(Long rowId);//进修改

	Long updateDictionary(Dictionary dictionary);//执行修改

	Integer checkDicCode(String dicCode, String parentKey);//字典编码唯一校验

	List<Dictionary> useDictionaryList();

}
