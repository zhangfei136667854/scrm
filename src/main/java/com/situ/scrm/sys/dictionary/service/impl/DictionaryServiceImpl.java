package com.situ.scrm.sys.dictionary.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionary.dao.DictionaryDao;
import com.situ.scrm.sys.dictionary.domain.Dictionary;
import com.situ.scrm.sys.dictionary.service.DictionaryService;
import com.situ.scrm.sys.syscount.util.SysCountUtils;
import com.situ.scrm.sys.sysresource.domain.SysResource;
import com.situ.scrm.utils.DAOUtils;
@Service
public class DictionaryServiceImpl implements Serializable, DictionaryService {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private SysCountUtils sysCountUtils;

	@Override
	@Transactional
	public Long saveDictionary(Dictionary dictionary) {
		// 得到字典索引
		String dicKey = sysCountUtils.buildDicKey();
		dictionary.setDicKey(dicKey);
		// 根据父类数据处理排序问题。
		String parentKey = dictionary.getParentKey();
		Integer maxOrder = dictionaryDao.getMaxOrder(parentKey);
		maxOrder = maxOrder == null ? 0 : maxOrder;
		dictionary.setDicOrder(maxOrder + 1);
		// 如果不是默认的ParentCode，则需要对parent类更新有子类数据信息
		if (!Dictionary.DEFAULT_PARENT_KEY.equals(parentKey)) {
			dictionaryDao.updateHasChild(parentKey, 1);
		}
		dictionary.setHasChild(0);
		dictionary.setActiveFlag(1);
		dictionary.setCreateBy("sys");
		dictionary.setCreateDate(new Date());
		dictionaryDao.save(dictionary);
		/*
		 * // 处理资源信息对应的 对象数据 List<SysActionInfo> actionInfoList =
		 * removeNullData(sysResource.getActionInfoList()); // 处理资源信息的动作数据
		 * handlerActionInfoListData(rescCode, actionInfoList);
		 */
		return dictionary.getRowId();
	}

	@Override
	public LayResult findAllDictionary() {
		List<Dictionary> allDictionaryList = new ArrayList<Dictionary>();
		// 将所有的资源信息build成Map<ParentCode,List<SysResource>>
		Map<String, List<Dictionary>> dictionaryMap = buildDictionaryMap();
		if (dictionaryMap != null) {
			// 先从map中将一级资源的集合取出。
			List<Dictionary> dictionaryList = dictionaryMap.get(Dictionary.DEFAULT_PARENT_KEY);
			// 遍历一级资源
			if (dictionaryList != null) {
				for (Dictionary dictionary : dictionaryList) {
					// 判断是否有子数据
					Integer hasChild = dictionary.getHasChild();
					// 如果有子数据
					if (hasChild == 1) {
						// 通过递归方法(重复调用本身)将所有资源的子数据处理成功。
						callBackChildList(dictionary, dictionaryMap);
					}
					allDictionaryList.add(dictionary);
				}
			}
		}
		return new LayResult(200, "ok", allDictionaryList);
	}

	/**
	 * @Title: callBackChildList
	 * @Description:(递归的方式拿到子类数据)
	 * @param parentCode
	 * @param resourceMap
	 * @return
	 */
	private void callBackChildList(Dictionary dictionary, Map<String, List<Dictionary>> dictionaryMap) {
		String parentKey = dictionary.getDicKey();
		List<Dictionary> childList = dictionaryMap.get(parentKey);
		if (childList != null) {
			for (Dictionary child : childList) {
				if (child.getHasChild() == 1) {
					callBackChildList(child, dictionaryMap);
				}
			}
			// 将拿到的子数据设置进去。
			dictionary.setChildren(childList);
		}
	}

	/**
	 * @Title: buildResourceMap
	 * @Description:(将资源数据转换成MAP格式的数据)
	 * @param resourceList
	 * @return Map<parentCode,List<SysResource>>
	 */
	private Map<String, List<Dictionary>> buildDictionaryMap() {
		// 将系统资源所有的数据都查询出来。
		List<Dictionary> dictionaryList = dictionaryDao.find();
		Map<String, List<Dictionary>> dictionaryMap = new HashMap<String, List<Dictionary>>();
		if (dictionaryList != null) {
			for (Dictionary dictionary : dictionaryList) {
				String parentKey = dictionary.getParentKey();
				List<Dictionary> list = dictionaryMap.get(parentKey);
				if (list == null) {
					list = new ArrayList<Dictionary>();
				}
				list.add(dictionary);
				dictionaryMap.put(parentKey, list);
			}
		}
		return dictionaryMap;
	}

	@Override
	public Dictionary getDictionaryById(Long rowId) {
		return dictionaryDao.get(rowId);
	}

	@Override
	public Integer doDeleteDictionary(Long rowId) {
		Dictionary deleteDictionary = dictionaryDao.get(rowId);
		String dicKey = deleteDictionary.getDicKey();
		String parentKey = deleteDictionary.getParentKey();
		dictionaryDao.delete(rowId);
		// 回调删除相关的数据
		//handlerDeleteDictionary(deleteDictionary);
		// 如果不是默认的父类CODE
		if (!parentKey.equals(Dictionary.DEFAULT_PARENT_KEY)) {
			// 修改父类的资源是否有子元素
			Integer hasChild = 0;
			List<Dictionary> childDictionaryList = dictionaryDao.findByParent(dicKey);
			if (childDictionaryList != null && !childDictionaryList.isEmpty()) {
				hasChild = 1;
			}
			dictionaryDao.updateHasChild(parentKey, hasChild);
		}
		return 1;
	}

	@Override
	public Dictionary getAllDictionaryById(Long rowId) {
		Dictionary dictionary = dictionaryDao.get(rowId);
		//List<SysActionInfo> actionInfoList = sysActionInfoDao.findByCode(sysResource.getRescCode());
		//sysResource.setActionInfoList(actionInfoList);
		String parentKey = dictionary.getParentKey();
		if (!parentKey.equals(Dictionary.DEFAULT_PARENT_KEY)) {
			Dictionary parentDictionary = dictionaryDao.getByCode(parentKey);
			dictionary.setParentName(parentDictionary.getDicCode());
		}
		return dictionary;
	}

	@Override
	public Long updateDictionary(Dictionary dictionary) {
		Long rowId = dictionary.getRowId();
		Dictionary editDictionary = dictionaryDao.get(rowId);
		editDictionary = DAOUtils.buildEditData(editDictionary, dictionary);
		editDictionary.setUpdateBy("sys");
		editDictionary.setUpdateDate(new Date());
		dictionaryDao.update(editDictionary);
		// 处理资源信息对应的 对象数据
		//List<SysActionInfo> actionInfoList = removeNullData(sysResource.getActionInfoList());
		// 处理资源信息的动作数据
		//handlerActionInfoListData(editResource.getRescCode(), actionInfoList);
		return rowId;
	}

	@Override
	public Integer checkDicCode(String dicCode, String parentKey) {
		Dictionary dictionary = dictionaryDao.getByNameAndParent(dicCode, parentKey);
		return dictionary == null ? 0 : 1;
	}

	@Override
	public List<Dictionary> useDictionaryList() {
		List<Dictionary> allDictionaryList = new ArrayList<Dictionary>();
		Map<String, List<Dictionary>> dictionaryMap = buildDictionaryMap();
		if (dictionaryMap != null) {
			List<Dictionary> dictionaryList = dictionaryMap.get("K000");
			if (dictionaryList != null) {
				for (Dictionary dictionary : dictionaryList) {
					// 判断是否有子数据
					Integer hasChild = dictionary.getHasChild();
					// 如果有子数据
					if (hasChild == 1) {
						// 通过递归方法(重复调用本身)将所有资源的子数据处理成功。
						callBackChildList(dictionary, dictionaryMap);
					}
					allDictionaryList.add(dictionary);
				}
			}

		}
		
		return allDictionaryList;
	}
	
	
	


}
