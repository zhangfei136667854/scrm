package com.situ.scrm.sys.dictionaries.service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionaries.domain.Dictionaries;

public interface DictionariesService {

	Integer checkDicValue(String dicValue);

	Long saveDic(Dictionaries dictionaries);

	LayResult findAllDictionaries();

	Dictionaries getDictionariesById(Long parentId);

	

	Long doUpdate(Dictionaries dic);

	Long doDelete(Long rowId);

}
