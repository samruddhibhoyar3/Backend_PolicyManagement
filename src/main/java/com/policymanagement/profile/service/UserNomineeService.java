package com.policymanagement.profile.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.policymanagement.profile.entity.UserNominee;

import com.policymanagement.profile.model.UserNomineeDTO;

@Service
public interface UserNomineeService {

	void addNominee(String username, UserNomineeDTO nomineeDTO);

	UserNomineeDTO getNominee(String username);

	void removeNominee(String username);
}
