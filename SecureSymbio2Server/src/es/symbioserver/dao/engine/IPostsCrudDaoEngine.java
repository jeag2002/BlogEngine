package es.symbioserver.dao.engine;

import java.util.List;

import es.symbioserver.beans.PostsBean;

public interface IPostsCrudDaoEngine {
	//Get PostsBean by Id
	public List<PostsBean> getPostsById(int id);
	
	//Get PostsBean by Uid
	public List<PostsBean> getPostsByUID(int uid);
	
	//Get a List of Beans for criteria
	public List<PostsBean> getPostsByCriteria (String criteria);
	
	//Delete Post
	public void deletePostsByUserAndId(int user, int id);
	
	//Update Post
	public String updatePostsById(int userid, int id, PostsBean pBean);
	
	//Insert Post
	public String insertPost(PostsBean pBean);
}
