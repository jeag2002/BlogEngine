package es.symbioserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import es.symbioserver.beans.PostsBean;
import es.symbioserver.service.IPostService;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/postService")
public class SymbioPostServerController {
	
	@Autowired
	private IPostService postService;
	
	private final Logger logger = LoggerFactory.getLogger(SymbioPostServerController.class);
	
	/**
	 * Create a post
	 */
	@ApiOperation(value="insertPost",nickname="insertPost")
	@RequestMapping(value = "/insertPost", method = RequestMethod.POST,  produces = "text/html")
	public HttpEntity<String> insertPost(
			@RequestBody PostsBean pBean){
		String response = "";
		logger.info("[SymbioPostServerControler] -- insertPost POST");
		response = postService.insertPost(pBean);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	/**
	 * Update a post
	 * @param userid id user 
	 * @param id  id post
	 * @param pBean data 
	 */
	@ApiOperation(value="updatePost",nickname="updatePost")
	@RequestMapping(value = "/updatePost/{userid}/{id}", method = RequestMethod.PUT,  produces = "text/html")
	public HttpEntity<String>  updatePost(
			@PathVariable("userid") int userid,
			@PathVariable("id") int id, 
			@RequestBody PostsBean pBean){
		logger.info("[SymbioPostServerControler] -- updatePost/[" + userid + "]/[" + id + "] PUT");
		String response = postService.updatePost(userid, id, pBean);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	/**
	 * Delete a post
	 * @param userid user
	 * @param id id post
	 */
	@ApiOperation(value="deletePost",nickname="deletePost")
	@RequestMapping(value = "/deletePost/{userid}/{id}", method = RequestMethod.DELETE)
	public void deletePost(@org.springframework.web.bind.annotation.PathVariable("userid") int userid,
			@org.springframework.web.bind.annotation.PathVariable("id") int id){
		logger.info("[SymbioPostServerControler] -- deletePost/["+userid+"]/["+id+"] DELETE");
		postService.deletePost(userid, id);		
	}
	
	
	/**
	 * Get post by ID
	 * @param id id post
	 */
	@ApiOperation(value="getPostById",nickname="getPostById")
	@RequestMapping(value = "/getPostById/{id}", method = RequestMethod.GET)
	
	public HttpEntity<List<PostsBean>> getPostById( 
			@org.springframework.web.bind.annotation.PathVariable("id") int id){	
		logger.info("[SymbioPostServerControler] -- getPostById/["+id+"] GET");
		List<PostsBean> data = new ArrayList<PostsBean>();
		data = postService.getPostById(id);
		return new ResponseEntity<List<PostsBean>>(data, HttpStatus.OK);
	}
	
	
	/**
	 * Get post by UID
	 * @param id id post
	 */
	@ApiOperation(value="getPostByUID",nickname="getPostByUID")
	@RequestMapping(value = "/getPostByUID/{uid}", method = RequestMethod.GET)
	
	public HttpEntity<List<PostsBean>> getPostByUID( 
			@org.springframework.web.bind.annotation.PathVariable("uid") int uid){	
		logger.info("[SymbioPostServerControler] -- getPostByUID/["+uid+"] GET");
		List<PostsBean> data = new ArrayList<PostsBean>();
		data = postService.getPostByUID(uid);
		return new ResponseEntity<List<PostsBean>>(data, HttpStatus.OK);
	}
	
	
	
	/**
	 * Looking for a list of post by title/content or a part of title/content
	 * @param criteria searching criteria.
	 */
	
	@ApiOperation(value="getPostsListByCriteria",nickname="getPostsListByCriteria")
	@RequestMapping(value = "/getPostsListByCriteria/{criteria}", method = RequestMethod.GET)
	public HttpEntity<List<PostsBean>> getPostListByCriteria( 
			@org.springframework.web.bind.annotation.PathVariable("criteria") String criteria){	
		
		logger.info("[SymbioPostServerControler] -- getPostsListByCriteria/[" + criteria +"] GET");
		List<PostsBean> dataList = new ArrayList<PostsBean>();
		dataList = postService.getPostsListByCriteria(criteria);
		
		return new ResponseEntity<List<PostsBean>>(dataList, HttpStatus.OK);
	}
	
	
	
	

}
