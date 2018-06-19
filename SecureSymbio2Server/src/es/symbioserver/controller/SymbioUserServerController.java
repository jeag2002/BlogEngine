package es.symbioserver.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.symbioserver.beans.UserBean;
import es.symbioserver.service.IUserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userService")
public class SymbioUserServerController {
	
	private final Logger logger = LoggerFactory.getLogger(SymbioUserServerController.class);
	
	@Autowired
	private IUserService service;
	
	/**
	 * Create User
	 * @param uBean insert data.
	 * @return id user created or insert user KO
	 */
	
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST,  produces = "text/html")
	@ApiOperation(value="insertUser",nickname="insertUser")
	public HttpEntity<String> insertUser(
			@Valid @RequestBody UserBean uBean){
		String response = "";
		logger.info("[SymbioUserServerControler] -- insertUser POST");
		response = service.createUser(uBean);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	/**
	 * Modify User
	 * @param UID id user
	 * @param uBean insert data
	 * return modify user OK/ modify user KO
	 */
	
	@RequestMapping(value = "/modifyUser/{UID}", method = RequestMethod.PUT,  produces = "text/html")
	@ApiOperation(value="modifyUser",nickname="modifyUser")
	public HttpEntity<String>  modifyUser(
			@PathVariable("UID") int userid,
			@Valid @RequestBody UserBean uBean){
		logger.info("[SymbioUserServerControler] -- updateUser/[" + userid + "] PUT");
		String response = service.modifyUser(userid, uBean);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	/**
	 * Get User By ID
	 * @param UID id user
	 * @return uBean data of user of ID or empty object. 
	 */
	@RequestMapping(value = "/getUserByUID/{UID}", method = RequestMethod.GET)
	@ApiOperation(value="getUserByUID",nickname="getUserByUID")
	public HttpEntity<UserBean>  getUserByUID(
			@PathVariable("UID") int UID){
		logger.info("[SymbioUserServerControler] -- getUserByUID/[" + UID + "] GET");
		UserBean uBean = service.getUserByUID(UID);
		return new ResponseEntity<UserBean>(uBean, HttpStatus.OK);
	}
	
	
	
}
