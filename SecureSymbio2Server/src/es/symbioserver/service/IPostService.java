package es.symbioserver.service;

import java.util.List;

import es.symbioserver.beans.PostsBean;

public interface IPostService {
	
	/**
	 * Get a post By ID
	 */
	public List<PostsBean> getPostById(int id);
	
	
	/**
	 * Get a post By UID
	 */
	public List<PostsBean> getPostByUID(int uid, String username);
	
	
	/**
	 * Get a list of post by Criteria
	 */
	public List<PostsBean> getPostsListByCriteria(String criteria);
	
	
	/**
	 * Delete a post By ID & User
	 */
	public void deletePost(int userid, String username, int id);
	
	
	/**
	 * Update a post By ID & User
	 */
	public String updatePost(int userid, String username, int id, PostsBean pBean);
	
	
	/**
	 * Insert a post
	 */
	public String insertPost(PostsBean pBean);
	
	

}
