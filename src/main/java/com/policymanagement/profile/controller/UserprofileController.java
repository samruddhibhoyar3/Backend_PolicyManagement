package com.policymanagement.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policymanagement.profile.model.UserprofileDTO;
import com.policymanagement.profile.service.UserprofileService;
import com.policymanagement.profile.util.UsernameGenerator;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api")
public class UserprofileController {
	@Autowired
	private UserprofileService userprofileService;

	@PostMapping("/profile")
	public ResponseEntity<String> createUser(@Valid @RequestBody UserprofileDTO profileDTO) {
		try {
			String generatedUsername = UsernameGenerator.generateUsername(profileDTO.getFirstName(),
					profileDTO.getLastName());
			profileDTO.setUsername(generatedUsername);

			userprofileService.createUser(profileDTO);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Profile created successfully! Username: " + generatedUsername);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create profile: " + e.getMessage());
		}
	}
}
