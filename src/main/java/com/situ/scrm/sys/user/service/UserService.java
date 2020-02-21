package com.situ.scrm.sys.user.service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.user.domain.User;

public interface UserService {

	LayResult findByPage(Integer page, Integer limit, User user);

	User findByUserLevel(int i);

	Long saveUser(User buildSearchParam);

	Long daDelete(Long rowId);

	User getByRowId(Long rowId);

	Long doUpdate(User user);

	Long updateIsLock(Integer isLock);

}
