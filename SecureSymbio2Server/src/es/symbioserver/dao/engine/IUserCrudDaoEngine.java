package es.symbioserver.dao.engine;

import es.symbioserver.beans.UserBean;

public interface IUserCrudDaoEngine {
	//Create User
	public String createUser(UserBean uBean);
	
	//Modify User
	public String modifyUser(int UID, UserBean uBean);
	
	//Get User By UID
	public UserBean getUserByUID(int UID);
	
}
