package es.symbioserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import es.symbioserver.beans.PostsBean;
import es.symbioserver.beans.UserBean;
import es.symbioserver.dao.crud.IPostsCrudDao;
import es.symbioserver.dao.engine.IPostsCrudDaoEngine;
import es.symbioserver.dao.engine.IUserCrudDaoEngine;


/**
 * Service management of post
 * @author Usuario
 *
 */

@Service
public class PostService implements IPostService{
	
	@Autowired
	private IPostsCrudDao queryPost;
	
	
	@Autowired
	private IPostsCrudDaoEngine queryPostEngine;
	
	@Autowired
	private IUserCrudDaoEngine queryUserEngine;
	
	private static final int SIZE_TITLE = 30;
	private static final int SIZE_CONTENT = 150;
	

	/**
	 * Get Post by ID
	 */
	@Override
	@Cacheable(value="postsCache",key="#id") //cache post with key id 
	public List<PostsBean> getPostById(int id){
		List<PostsBean> results = queryPostEngine.getPostsById(id);
		return results;
	}
	
	/**
	 * Get a List of Post By Criteria
	 */
	@Override
	@Cacheable(value="postsCache",key="#criteria") //cache list of post with key "criteria"
	public List<PostsBean> getPostsListByCriteria(String criteria) {
		List<PostsBean> results = queryPostEngine.getPostsByCriteria(criteria);
		return results;
	}
	
	/**
	 * Delete post
	 */
	@Override
	@CacheEvict(value="postsCache",key="#id") //delete cache value with key "id" if there is a delete
	public void deletePost(int userid, String username, int id) {
		
		UserBean uBean = queryUserEngine.getUserByName(username);
		if (uBean.getId() == id){
			queryPostEngine.deletePostsByUserAndId(userid, id);
		}
		
		
	}

	/**
	 * Update post
	 */
	@Override
	@CacheEvict(value="postsCache",key="#id")  //delete cache value with key "id" if there is an update
	public String updatePost(int userid, String username, int id, PostsBean pBean) {
		
		//Title length is 30 or less
		if (pBean.getTitle()==null){
			pBean.setTitle(" ");
		}else if (pBean.getTitle().length() > this.SIZE_TITLE){
			pBean.setTitle(pBean.getTitle().substring(0, this.SIZE_TITLE));
		}
		
		//Content length is 150 or less
		if (pBean.getContent()==null){
			pBean.setContent(" ");
		}else if (pBean.getContent().length() > this.SIZE_CONTENT){
			pBean.setContent(pBean.getContent().substring(0, this.SIZE_CONTENT));
		}
		
		UserBean uBean = queryUserEngine.getUserByName(username);
		
		String res = "";
		
		if (uBean.getId() == id){
			res = queryPostEngine.updatePostsById(userid,id, pBean); 
		}else{
			res = "Update KO";
		}
		
		return res;
	}
	
	/**
	 * Insert post
	 */
	@Override
	@CacheEvict(value="postsCache" , allEntries = true) //delete all cache information when there is an insert 
	public String insertPost(PostsBean pBean) {
		
		//Title length is 30 or less
		if (pBean.getTitle()==null){
			pBean.setTitle(" ");
		}else if (pBean.getTitle().length() > this.SIZE_TITLE){
			pBean.setTitle(pBean.getTitle().substring(0, this.SIZE_TITLE));
		}
		
		//Content length is 150 or less
		if (pBean.getContent()==null){
			pBean.setContent(" ");
		}else if (pBean.getContent().length() > this.SIZE_CONTENT){
			pBean.setContent(pBean.getContent().substring(0, this.SIZE_CONTENT));
		}
		
		
		return queryPostEngine.insertPost(pBean);
	}
	
	//delete all cache information after 5 mins
	@Scheduled(fixedRate = 300000)
	//@Scheduled(fixedRate = 10000) --> arbritrary value just for testing purposes.
	@CacheEvict(value = "postsCache" , allEntries = true)
	public void clearCache() {      
	}
	
	/**
	 * Get Posts By UID
	 */
	@Override
	public List<PostsBean> getPostByUID(int uid, String username) {
		
		String res = "";
		UserBean uBean = queryUserEngine.getUserByName(username);
		List<PostsBean> results = new ArrayList<PostsBean>();
		if (uBean.getId() == uid){
			results = queryPostEngine.getPostsByUID(uid);
		}
		return results;
	}
	

	
	
	
}
