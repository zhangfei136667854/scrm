package com.situ.scrm.sys.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.domain.Pagination;
import com.situ.scrm.sys.user.domain.User;

@Repository
public interface UserDao {

	User findByLevel(int userLevel);

	Long save(User buildSearchParam);

	Integer get();
	
	List<User> findBYpage(@Param("pagination") Pagination pagination, @Param("user") User user);

	Long delete(Long rowId);

	User getUser(Long rowId);

	Long update(User user);

	Long updateByIsLock(Integer isLock);


}
