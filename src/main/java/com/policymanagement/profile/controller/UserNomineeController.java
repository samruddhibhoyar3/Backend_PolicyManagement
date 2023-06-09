package com.policymanagement.profile.controller;

import com.policymanagement.profile.model.UserNomineeDTO;
import com.policymanagement.profile.service.UserNomineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/{username}/nominee")
public class UserNomineeController {

	private final UserNomineeService userNomineeService;

	@Autowired
	public UserNomineeController(UserNomineeService userNomineeService) {
		this.userNomineeService = userNomineeService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addNominee(@PathVariable("username") String username, @RequestBody UserNomineeDTO nomineeDTO) {
		userNomineeService.addNominee(username, nomineeDTO);
	}

	@GetMapping
	public UserNomineeDTO getNominee(@PathVariable("username") String username) {
		return userNomineeService.getNominee(username);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeNominee(@PathVariable("username") String username) {
		userNomineeService.removeNominee(username);
	}
}
