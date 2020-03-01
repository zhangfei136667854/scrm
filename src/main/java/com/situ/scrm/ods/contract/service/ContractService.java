package com.situ.scrm.ods.contract.service;

import javax.servlet.http.HttpSession;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.ods.contract.domain.Contract;

public interface ContractService {

	Long doPost(Contract cont,HttpSession session);

	LayResult doFind(Integer page, Integer limit, Contract cont,HttpSession session);

}
