package com.wcecil.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wcecil.common.settings.ApplicationComponent;
import com.wcecil.data.objects.User;
import com.wcecil.data.objects.UserToken;
import com.wcecil.data.repositiories.UsersRepository;
import com.wcecil.webservice.service.AuthenticationService;

//TODO make secure
@RestController
public class UserController {
	@Autowired
	UsersRepository usersRepo;

	@Autowired
	ApplicationComponent context;
	
	@Autowired
	AuthenticationService authService;
	
	@RequestMapping("/players/all")
	public List<User> listAll() {
		if(context.getAllowDebug()){
		return usersRepo.listAll();
		}
		throw new IllegalStateException("Debug Method");
	}
	
	@RequestMapping("/players/auth")
	public User auth(@RequestParam("username") String userName,
			@RequestParam("password") String password) {
		return usersRepo.auth(userName, password);
	}
	
	@RequestMapping("/players/token")
	public UserToken authToken(@RequestParam("username") String userName,
			@RequestParam("password") String password) {
		User user = usersRepo.auth(userName, password);
		
		return usersRepo.createTokenForUserId(user.getId());
	}
	
	@RequestMapping("/players/me")
	public User authToken(@RequestParam("token") String token) {
		String userId = authService.getUserFromToken(token);
		User user = usersRepo.getUser(userId);
		return user;
	}

	@RequestMapping("/players/new")
	public User createNew(@RequestParam("username") String userName,
			@RequestParam("password") String password,
			@RequestParam(value = "name", required = false) String name) {
		return usersRepo.createNew(userName, password, name);
	}
	
	@RequestMapping("/players/add/game")
	public User createNew(@RequestParam("userId") String userId,
			@RequestParam("gameId") String gameId
			) {
		return usersRepo.addGameToUser(userId, gameId);
	}

}