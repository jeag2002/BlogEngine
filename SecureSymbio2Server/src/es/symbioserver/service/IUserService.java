package es.symbioserver.service;

import es.symbioserver.beans.UserBean;

public interface IUserService {
	public String createUser(UserBean uBean);
	public String modifyUser(int UID, UserBean uBean);
	public UserBean getUserByUID(int UID);
}
