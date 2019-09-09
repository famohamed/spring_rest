package com.example.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.app.ws.exceptions.UserClassException;
import com.example.app.ws.ui.model.req.*;
import com.example.app.ws.ui.model.resp.*;

@RestController
@RequestMapping("users")
public class UserController {
	
	Map<String, UserRest> users;

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "-1", required = false) int page,
			@RequestParam(value = "limit", required = false, defaultValue = "-1") int limit) {
		return "Get User: Page=" + page + " and limit = " + limit;
	}

	@GetMapping(path = "{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		UserRest lUserRest = new UserRest();
		
		//String firstName = null;
		//int firstNameLen = firstName.length();
		if (true) throw new UserClassException("A user exception generated");
		
		if (users == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else if (users.containsKey(userId)) {
			lUserRest = users.get(userId);
			return new ResponseEntity<UserRest>(lUserRest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserReq lUserReq) {

		UserRest lUserRest = new UserRest();

		lUserRest.setFirstName(lUserReq.getFirstName());
		lUserRest.setLastName(lUserReq.getLastName());
		lUserRest.setEmail(lUserReq.getEmail());
		
		String userId = UUID.randomUUID().toString();
		lUserRest.setUserId(userId);
		
		if (users == null) users = new HashMap<>();
		users.put(userId, lUserRest);

		return new ResponseEntity<UserRest>(lUserRest, HttpStatus.OK);
	}

	@PutMapping(path = "{userId}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserReq lUpdateUserReq) {
		
		UserRest lUserRest = new UserRest();
		
		if (users.containsKey(userId)) {
			lUserRest = users.get(userId);
			users.remove(userId);
			
			lUserRest.setFirstName(lUpdateUserReq.getFirstName());
			lUserRest.setLastName(lUpdateUserReq.getLastName());
			
			users.put(userId, lUserRest);
			
			return new ResponseEntity<UserRest>(lUserRest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping(path = "{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		
		if (users == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (users.containsKey(userId)) {
			users.remove(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
