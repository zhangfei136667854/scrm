package com.situ.scrm.sys.dictionary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.dictionary.domain.Dictionary;
@Repository
public interface DictionaryDao extends BaseDao<Dictionary> {

	Integer getMaxOrder(String parentKey);//根据parentKey得到最大的排序

	void updateHasChild(@Param("dicKey") String dicKey, @Param("hasChild") Integer hasChild);//更新是否有子元素

	List<Dictionary> findByParent(String parentKey);//根据parentKey查询子类数据

	Dictionary getByCode(String dicKey);

	Dictionary getByNameAndParent(String dicCode, String parentKey);//唯一校验字典编码

	

	

}
