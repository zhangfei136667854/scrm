package com.situ.scrm.sys.dictionaries.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionaries.dao.DictionariesDao;
import com.situ.scrm.sys.dictionaries.domain.Dictionaries;
import com.situ.scrm.sys.dictionaries.service.DictionariesService;
import com.situ.scrm.syscount.util.SysCountUtils;

@Service
public class DictionariesServiceImpl implements  DictionariesService {
	@Autowired
	private DictionariesDao dictionariesDao;
	@Autowired
	private SysCountUtils sysCountUtils;
	
	@Override
	public Integer checkDicValue(String dicValue) {
		Dictionaries dictionaries = dictionariesDao.getByName(dicValue);
		// 判断如果不等于null 则返回1，表示已经别使用了。
		return dictionaries != null ? 1 : 0;
	}


	@Override
	public Long saveDic(Dictionaries dictionaries) {	
		// 得到资源编号
		String dictKey = sysCountUtils.buildDictKey();
		dictionaries.setDicKey(dictKey);
		String parentKey = dictionaries.getParentKey();
		Integer maxOrder = dictionariesDao.getMaxOrder(parentKey);
		maxOrder = maxOrder == null ? 0 : maxOrder;
		dictionaries.setOrderIndex(maxOrder + 1);
		// 如果不是默认的parentKey，则需要对parent类更新有子类数据信息
		if (!Dictionaries.DEFAULT_PARENT_KEY.equals(parentKey)) {
			dictionariesDao.updateHasChild(parentKey, 1);
		}
		dictionaries.setCreateBy("sys");
		dictionaries.setCreateDate(new Date());
		dictionaries.setActiveFlag(1);
		dictionaries.setHasChild(0);

		return dictionariesDao.save(dictionaries);
	}


	@Override
	public LayResult findAllDictionaries() {
		List<Dictionaries> allDictionaryList = new ArrayList<Dictionaries>();
		Map<String, List<Dictionaries>> dictionaryMap = buildDictionaryMap();
		if (dictionaryMap != null) {
			List<Dictionaries> dictionariesList = dictionaryMap.get(Dictionaries.DEFAULT_PARENT_KEY);
			if (dictionariesList != null) {
				for (Dictionaries dictionaries : dictionariesList) {
					// 判断是否有子数据
					Integer hasChild = dictionaries.getHasChild();
					// 如果有子数据
					if (hasChild == 1) {
						// 通过递归方法(重复调用本身)将所有资源的子数据处理成功。
						callBackChildList(dictionaries, dictionaryMap);
					}
					allDictionaryList.add(dictionaries);
				}
			}

		}
		return new LayResult(200, "ok", allDictionaryList);
	}
	/**
	 * @Title: buildDictionaryMap
	 * @Description:(将资源数据转换成MAP格式的数据)
	 * @param dictionaryList
	 * @return Map<parentKey,List<Dictionaries>>
	 */
	private Map<String, List<Dictionaries>> buildDictionaryMap() {
		// 将系统资源所有的数据都查询出来。
		List<Dictionaries> dictionariesList = dictionariesDao.find();
		Map<String, List<Dictionaries>> dictionaryMap = new HashMap<String, List<Dictionaries>>();
		if (dictionariesList != null) {
			for (Dictionaries dictionaries : dictionariesList ) {

				String parentKey = dictionaries.getParentKey();

				List<Dictionaries> list = dictionaryMap.get(parentKey);
				if (list == null) {
					list = new ArrayList<Dictionaries>();
				}
				list.add(dictionaries);

				dictionaryMap.put(parentKey, list);
			}
		}
		return dictionaryMap;
	}
	
	/**
	 * @Title: callBackChildList
	 * @Description:(递归的方式拿到子类数据,设置Children属性)
	 * @param parentKey
	 * @param dictionaryMap
	 * @return
	 */
	private void callBackChildList(Dictionaries dictionaries, Map<String, List<Dictionaries>> dictionariesMap) {
		String parentKey = dictionaries.getDicKey();
		List<Dictionaries> childList = dictionariesMap.get(parentKey);
		if (childList != null) {
			for (Dictionaries child : childList) {
				if (child.getHasChild() == 1) {
					callBackChildList(child, dictionariesMap);
				}
			}
			// 将拿到的子数据设置进去。
			dictionaries.setChildren(childList);
		}
	}
	
	/**
	 * @Title: getAllDictionaryById
	 * @Description:(根据主键查询Dictionary对象并给parentName赋值)
	 * @param rowId
	 * @return
	 */
	@Override
	public Dictionaries getDictionariesById(Long rowId) {

		Dictionaries dictionaries = dictionariesDao.get(rowId);

		String parentKey = dictionaries.getParentKey();
		if (!parentKey.equals(Dictionaries.DEFAULT_PARENT_KEY)) {
			Dictionaries parentDictionaries = dictionariesDao.getByKey(parentKey);
			dictionaries.setParentName(parentDictionaries.getDicValue());
		}
		return dictionaries;
	}

	
	
}
