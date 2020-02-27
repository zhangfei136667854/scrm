package com.situ.scrm.sys.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.user.domain.User;



@Repository
public interface UserDao extends BaseDao<User> {
	/**
	 * @Title: getUserByCode 
	 * @Description:(根据UserCode查询实例)
	 * @param userCode
	 * @return
	 */
	User getUserByCode(String userCode);

	List<User> findByLevel(int i);//根据用户等级查询一个用户实例

	void update4Lock(@Param("rowId")Long rowId, @Param("isLock")Integer isLock);

	User findByCodeAndPass(@Param("userCode")String userCode, @Param("userPass")String encode);//登录账号和密码


}
