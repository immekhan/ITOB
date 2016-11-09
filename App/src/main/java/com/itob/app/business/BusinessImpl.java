package com.itob.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itob.app.info.BusinessResponse;
import com.itob.app.managers.ManagerImpl;
@Service
public class BusinessImpl {
	
	@Autowired
	private ManagerImpl managerImpl;
	
	public BusinessResponse getData() {
		BusinessResponse businessResponse = managerImpl.getData();
		return businessResponse;
	}

}
