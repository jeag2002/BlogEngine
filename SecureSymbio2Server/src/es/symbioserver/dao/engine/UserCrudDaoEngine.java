package es.symbioserver.dao.engine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.symbioserver.beans.UserBean;

@Service
public class UserCrudDaoEngine implements IUserCrudDaoEngine {

	private final Logger logger = LoggerFactory.getLogger(PostCrudDaoEngine.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	/**
	 * Create Users
	 * @param uBean Data of new user
	 * @return index of new user. if error "create user KO"
	 */
	public String createUser(UserBean uBean) {
		
		String resolve = "";
		
		try{
			
			if (this.existUser(uBean)==-1){
			
				Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				String query = "INSERT INTO user(name,role,email,password,enabled) VALUES ";
				query += "('" + uBean.getName() + "','" + uBean.getRole() + "','" + uBean.getEmail() + "','" + uBean.getPassword() + "',1)";
				int res = statement.executeUpdate(query);
				logger.info("[UserCrudDaoEngine-createUser] query (" + query + ") res (" + res + ")");
				statement.close();
				connection.close();
				
				int index = this.existUser(uBean);
				resolve = String.valueOf(index);
			
			}else{
				logger.warn("[UserCrudDaoEngine-createUser] user already exists!");
				resolve = "create user KO";
			}
			
		}catch(Exception e){
			
			logger.warn("[UserCrudDaoEngine-createUser] error (" + e.getMessage() + ")");
			resolve = "create user KO";
		}finally{
			return resolve;
		}
			
	}

	@Override
	/**
	 * Modify password. (Different requirement of the assignment)
	 * 
	 */
	public String modifyUser(int UID, UserBean uBean) {
		
		String resolve = "";
		
		try{
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			if (!uBean.getPassword().equals("")){
				String query = "update user set ";
				query += " password = '" + uBean.getPassword() + "' where id = " + String.valueOf(UID);
				int res = statement.executeUpdate(query);
				logger.info("[UserCrudDaoEngine-modifyUser] query (" + query + ") res (" + res + ")");
			}
			
			resolve = "Update user OK";
			statement.close();
			connection.close();
			
		}catch(Exception e){
			
			logger.warn("[UserCrudDaoEngine-modifyUser] error (" + e.getMessage() + ")");
			resolve = "Update user KO";
			
		}finally{
			return resolve;
		}
		
		
	}

	@Override
	/**
	 * Get User By ID 
	 * @param UID
	 */
	public UserBean getUserByUID(int UID) {
		
		UserBean uBean = new UserBean();
		
		
		try{
		
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			String query = "select u.id, u.name, u.role, u.email, u.password from user u where u.id = " + String.valueOf(UID);
			logger.info("[UserCrudDaoEngine-getUserByUID] query (" + query + ")");
			
			ResultSet rs = statement.executeQuery(query);
			
			boolean isData = rs.next();
			
			if (isData){
				uBean = new UserBean();
				uBean.setId(rs.getInt("id"));
				uBean.setName(rs.getString("name"));
				uBean.setRole(rs.getString("role"));
				uBean.setEmail(rs.getString("email"));
				uBean.setPassword(rs.getString("password"));
			}else{
				logger.warn("[UserCrudDaoEngine-getUserByUID] resultset empty");
			}
	
			statement.close();
			connection.close();	
		}catch(Exception e){
			logger.warn("[UserCrudDaoEngine-getUserByUID] error (" + e.getMessage() + ")");
			uBean = new UserBean();
		}finally{
			return uBean;
		}
	}
	
	
	
	/**
	 * Get User By Name 
	 * @param UID
	 */
	public UserBean getUserByName(String name) {
		
		UserBean uBean = new UserBean();
		
		
		try{
		
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			String query = "select u.id, u.name, u.role, u.email, u.password from user u where u.name = '" + name + "'";
			logger.info("[UserCrudDaoEngine-getUserByName] query (" + query + ")");
			
			ResultSet rs = statement.executeQuery(query);
			
			boolean isData = rs.next();
			
			if (isData){
				uBean = new UserBean();
				uBean.setId(rs.getInt("id"));
				uBean.setName(rs.getString("name"));
				uBean.setRole(rs.getString("role"));
				uBean.setEmail(rs.getString("email"));
				uBean.setPassword(rs.getString("password"));
			}else{
				logger.warn("[UserCrudDaoEngine-getUserByName] resultset empty");
			}
	
			statement.close();
			connection.close();	
		}catch(Exception e){
			logger.warn("[UserCrudDaoEngine-getUserByName] error (" + e.getMessage() + ")");
			uBean = new UserBean();
		}finally{
			return uBean;
		}
	}
	
	
	
	
	/**
	 * Evaluates if exist user. If yes returns UID; if not returns -1
	 * @param uBean
	 * @return
	 */
	
	
	private int existUser(UserBean uBean){
		
		int uid = -1;
		
		try{
		
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
				
			String query = "select u.id from user u where u.name = '" + uBean.getName() + "' and u.email = '" + uBean.getEmail()  + "'";
			logger.info("[UserCrudDaoEngine-existUser] query (" + query + ")");
			
			ResultSet rs = statement.executeQuery(query);
			
			boolean isData = rs.next();
			
			if (isData){
				uid = rs.getInt("id");
			}else{
				logger.warn("[UserCrudDaoEngine-getUserByUID] resultset empty");
			}
	
			statement.close();
			connection.close();
			
		}catch(Exception e){
			logger.warn("[UserCrudDaoEngine-getUserByUID] error (" + e.getMessage() + ")");
			uid = -1;
		}finally{
			return uid;
		}
		
		
		
		
		
		
		
	}
	

}
