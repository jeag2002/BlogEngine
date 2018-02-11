package es.symbioserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.symbioserver.beans.UserBean;
import es.symbioserver.dao.engine.IUserCrudDaoEngine;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserCrudDaoEngine uCDaoEngine;
	
	private static final int LENGTH_NAME = 50;
	private static final int LENGTH_MAIL = 50;
	private static final int LENGTH_PASS = 50;
	private static final int LENGTH_ROLE = 50;
	
	@Override
	/**
	 * Create User
	 * @param uBean input UserData
	 */
	public String createUser(UserBean uBean) {
		
		if (uBean.getName()==null){uBean.setName("");}
		else if (uBean.getName().trim().length() > LENGTH_NAME){uBean.setName(uBean.getName().substring(0, LENGTH_NAME));}
		
		if (uBean.getEmail()==null){uBean.setEmail("");}
		else if (uBean.getEmail().trim().length() > LENGTH_MAIL){uBean.setEmail(uBean.getEmail().substring(0, LENGTH_MAIL));}
		
		if (uBean.getPassword()==null){uBean.setPassword("");}
		else if (uBean.getPassword().trim().length() > LENGTH_PASS){uBean.setPassword(uBean.getPassword().substring(0, LENGTH_PASS));}
		
		if (uBean.getRole()==null){uBean.setRole("");}
		else if (uBean.getRole().trim().length() > LENGTH_ROLE){uBean.setRole(uBean.getRole().substring(0, LENGTH_ROLE));}
		
		return uCDaoEngine.createUser(uBean);
	}

	@Override
	/**
	 * Modify user 
	 * @param UID id user
	 * @param uBean userBean
	 */
	public String modifyUser(int UID, UserBean uBean) {
		
		if (uBean.getName()==null){uBean.setName("");}
		else if (uBean.getName().trim().length() > LENGTH_NAME){uBean.setName(uBean.getName().substring(0, LENGTH_NAME));}
		
		if (uBean.getEmail()==null){uBean.setEmail("");}
		else if (uBean.getEmail().trim().length() > LENGTH_MAIL){uBean.setEmail(uBean.getEmail().substring(0, LENGTH_MAIL));}
		
		if (uBean.getPassword()==null){uBean.setPassword("");}
		else if (uBean.getPassword().trim().length() > LENGTH_PASS){uBean.setPassword(uBean.getPassword().substring(0, LENGTH_PASS));}
		
		if (uBean.getRole()==null){uBean.setRole("");}
		else if (uBean.getRole().trim().length() > LENGTH_ROLE){uBean.setRole(uBean.getRole().substring(0, LENGTH_ROLE));}
		
		return uCDaoEngine.modifyUser(UID, uBean);
	}

	@Override
	/**
	 * Get User By ID
	 * @param UID id user
	 */	
	public UserBean getUserByUID(int UID) {
		return uCDaoEngine.getUserByUID(UID);
	}

}
