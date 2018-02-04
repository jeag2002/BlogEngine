package es.symbioserver.dao.engine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.symbioserver.beans.PostsBean;


/**
 * DB2 Engine Posts table management
 * @author Usuario
 */

@Service
public class PostCrudDaoEngine implements IPostsCrudDaoEngine {
	
	
	private final Logger logger = LoggerFactory.getLogger(PostCrudDaoEngine.class);
	
	private SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	/**
	 * Get post by ID Post
	 */
	public List<PostsBean> getPostsById(int id) {
		
		List<PostsBean> beans = new ArrayList<PostsBean>();
		
		try{
			
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			//Get the post information related by id parameter.
			String query = "select p.id, p.title, p.content, p.userid, u.name, p.date from posts p inner join user u on (p.userid = u.id) where p.id = " + String.valueOf(id);
			logger.info("[PostCrudDaoEngine-getPostsById] query (" + query + ")");
			
			ResultSet rs = statement.executeQuery(query);
			
			boolean isData = rs.next();
			
			if (isData){
				PostsBean pBean = new PostsBean();
				pBean.setId(rs.getInt("id"));
				pBean.setTitle(rs.getString("title"));
				pBean.setContent(rs.getString("content"));
				pBean.setUserid(rs.getInt("userid"));
				pBean.setUsername(rs.getString("name"));
				pBean.setDate(rs.getString("date"));
				beans.add(pBean);
			}else{
				logger.warn("[PostCrudDaoEngine-getPostsById] resultset empty");
			}
			
			statement.close();
			connection.close();
			
		}catch(Exception e){
			logger.warn("[PostCrudDaoEngine-getPostsById] error (" + e.getMessage() + ")");
			beans = new ArrayList<PostsBean>();
		}finally{
			return beans;
		}
	}
	
	/**
	 * Get Posts By UID
	 */
	@Override
	public List<PostsBean> getPostsByUID(int uid) {
		
		List<PostsBean> beans = new ArrayList<PostsBean>();
		
		try{
			
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			//Get the list of post of user UID
			String query = "select p.id, p.title, p.content, p.userid, p.date from posts p where p.userid = " + String.valueOf(uid);
			logger.info("[PostCrudDaoEngine-getPostsByUID] query (" + query + ")");
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				PostsBean pBean = new PostsBean();
				pBean.setId(rs.getInt("id"));
				pBean.setTitle(rs.getString("title"));
				pBean.setContent(rs.getString("content"));
				pBean.setUserid(rs.getInt("userid"));
				pBean.setUsername("");
				pBean.setDate(rs.getString("date"));
				beans.add(pBean);
			}
			
			statement.close();
			connection.close();
			
		}catch(Exception e){
			logger.warn("[PostCrudDaoEngine-getPostsByUID] error (" + e.getMessage() + ")");
			beans = new ArrayList<PostsBean>();
		}finally{
			return beans;
		}
	}
	
	
	

	@Override
	
	/**
	 * Get Posts By Criteria
	 */
	public List<PostsBean> getPostsByCriteria(String criteria) {
		
		List<PostsBean> beans = new ArrayList<PostsBean>();
		
		try{
			
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			//Get the list of post that totally or partially match the fields "title" and "content" with the parameter "criteria"
			String query = "select p.id, p.title, p.content, p.userid, u.name, p.date from posts p inner join user u on (p.userid = u.id) where p.title like '%" + criteria + "%' or p.content like '%" + criteria + "%'";
			logger.info("[PostCrudDaoEngine-getPostsByCriteria] query (" + query + ")");
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				PostsBean pBean = new PostsBean();
				pBean.setId(rs.getInt("id"));
				pBean.setTitle(rs.getString("title"));
				pBean.setContent(rs.getString("content"));
				pBean.setUserid(rs.getInt("userid"));
				pBean.setUsername(rs.getString("name"));
				pBean.setDate(rs.getString("date"));
				beans.add(pBean);
			}
			
			statement.close();
			connection.close();
			
		}catch(Exception e){
			logger.warn("[PostCrudDaoEngine-getPostsByCriteria] error (" + e.getMessage() + ")");
			beans = new ArrayList<PostsBean>();
		}finally{
			return beans;
		}
		
	}

	@Override
	
	/**
	 * Post Deleted
	 * @param user user creator of post  
	 * @param id id post
	 */
	public void deletePostsByUserAndId(int user, int id) {
		
		try{
			
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			String query = "delete from Posts where id = " + id + " and userid = " + user;
			int res = statement.executeUpdate(query);
			logger.info("[PostCrudDaoEngine-deletePostsByUserAndId] query (" + query + ") res (" + res + ")");
			statement.close();
			connection.close();
			
		}catch(Exception e){
			logger.warn("[PostCrudDaoEngine-getPostsByCriteria] error (" + e.getMessage() + ")");
		}
	}

	
	
	
	
	@Override
	/**
	 * Post Update
	 * @param id id post
	 * @param pBean dataUpdate
	 */	
	
	public String updatePostsById(int userid, int id, PostsBean pBean) {
		// TODO Auto-generated method stub
		
		String resolve = "";
		
		try{
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			if (!pBean.getTitle().trim().equals("") || !pBean.getContent().trim().equals("") ){
				String query = "update posts set ";
				if (!pBean.getTitle().trim().equals("")){query += " title = '" + pBean.getTitle()  + "'";}
				if (!pBean.getContent().trim().equals("")){
					if (!pBean.getTitle().trim().equals("")){query += ",";}	
					query += " content = '" + pBean.getContent() + "'";
				}
				query += " ,date = '" + dFormat.format(new Date()) + "'";
				query += " where userid = " + String.valueOf(userid) + " and id = " + String.valueOf(id);
				int res = statement.executeUpdate(query);
				logger.info("[PostCrudDaoEngine-updatePostsById] query (" + query + ") res (" + res + ")");
			}
			
			resolve = "Update OK";
			statement.close();
			connection.close();
			
		}catch(Exception e){
			
			logger.warn("[PostCrudDaoEngine-updatePostsById] error (" + e.getMessage() + ")");
			resolve = "Update KO";
			
		}finally{
			return resolve;
		}
		
		
		
		
		
	}

	@Override
	/**
	 * Insert new Post
	 * @param pBean data 
	 */
	public String insertPost(PostsBean pBean) {
		
		String resolve="";
		
		try{
			
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			String query = "INSERT INTO Posts (title,content,userid, date) VALUES";
			query += "('" + pBean.getTitle() + "','" + pBean.getContent() + "'," + pBean.getUserid() + ",'" + dFormat.format(new Date()) + "')";
			
			int res = statement.executeUpdate(query);
			logger.info("[PostCrudDaoEngine-insertPost] query (" + query + ") res (" + res + ")");
			
			resolve = "Insert OK";
			statement.close();
			connection.close();
			
		}catch(Exception e){
			
			logger.warn("[PostCrudDaoEngine-insertPost] error (" + e.getMessage() + ")");
			resolve = "Insert KO";
			
			
		}finally{
			return resolve;
		}
		
	}


	
		
	
	
}
