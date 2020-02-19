package com.situ.scrm.sys.dictionaries.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.sys.dictionaries.domain.Dictionaries;

@Repository
public interface DictionariesDao {

	Dictionaries getByName(String dicValue);

	Long save(Dictionaries dictionaries);

	Integer getMaxOrder(String parentKey);

	void updateHasChild(@Param("dicKay")String dicKay, @Param("hasChild") Integer hasChild);

	List<Dictionaries> find();

	Dictionaries getByKey(String dicKay);

	Dictionaries get(Long rowId);

}
