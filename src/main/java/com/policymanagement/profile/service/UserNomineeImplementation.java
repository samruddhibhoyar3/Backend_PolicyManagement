package com.policymanagement.profile.service;

import com.policymanagement.profile.dao.UserNomineeDAO;
import com.policymanagement.profile.entity.UserNominee;
import com.policymanagement.profile.entity.Userprofile;
import com.policymanagement.profile.exception.ResourceNotFoundException;
import com.policymanagement.profile.model.UserNomineeDTO;
import com.policymanagement.profile.service.UserNomineeService;
import com.policymanagement.profile.service.UserprofileService;

import java.util.List;
import java.util.Optional;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNomineeImplementation implements UserNomineeService {

	private final UserNomineeDAO userNomineeDAO;
	private final UserprofileService userprofileService;

	@Autowired
	public UserNomineeImplementation(UserNomineeDAO userNomineeDAO, UserprofileService userprofileService) {
		this.userNomineeDAO = userNomineeDAO;
		this.userprofileService = userprofileService;
	}

	@Override
	public void addNominee(String username, UserNomineeDTO nomineeDTO) {
	    Userprofile userprofile = userprofileService.getUserByUsername(username);

	    UserNominee userNominee = new UserNominee();
	    userNominee.setFullName(nomineeDTO.getFullName());
	    userNominee.setDateOfBirth(nomineeDTO.getDateOfBirth());
	    userNominee.setGender(nomineeDTO.getGender());
	    userNominee.setIdProofType(nomineeDTO.getIdProofType());
	    userNominee.setIdProofDocument(nomineeDTO.getIdProofDocument());
	    userNominee.setNationality(nomineeDTO.getNationality());
	    userNominee.setNomineeRelation(nomineeDTO.getNomineeRelation());
	    userNominee.setUserProfile(userprofile); // Set the userprofile for the nominee

	    userNomineeDAO.save(userNominee);
	}

	@Override
	public UserNomineeDTO getNominee(String username) {
	    Userprofile userprofile = userprofileService.getUserByUsername(username);
	    List<UserNominee> userNominees = userNomineeDAO.findByUserProfile(userprofile);

	    if (userNominees.isEmpty()) {
	        throw new ResourceNotFoundException("No nominee found for the given username: " + username);
	    }

	    UserNominee userNominee = userNominees.get(0);

	    UserNomineeDTO nomineeDTO = new UserNomineeDTO();
	    nomineeDTO.setFullName(userNominee.getFullName());
	    nomineeDTO.setDateOfBirth(userNominee.getDateOfBirth());
	    nomineeDTO.setGender(userNominee.getGender());
	    nomineeDTO.setIdProofType(userNominee.getIdProofType());
	    nomineeDTO.setIdProofDocument(userNominee.getIdProofDocument());
	    nomineeDTO.setNationality(userNominee.getNationality());
	    nomineeDTO.setNomineeRelation(userNominee.getNomineeRelation());

	    return nomineeDTO;
	}


	@Override
	public void removeNominee(String username) {
	    Userprofile userprofile = userprofileService.getUserByUsername(username);
	    List<UserNominee> userNomineeList = userNomineeDAO.findByUserProfile(userprofile);

	    if (userNomineeList.isEmpty()) {
	        throw new ResourceNotFoundException("No nominee found for the given username: " + username);
	    }

	    UserNominee userNominee = userNomineeList.get(0); // Assuming you only want to remove the first nominee

	    userNomineeDAO.delete(userNominee);
	}
}
