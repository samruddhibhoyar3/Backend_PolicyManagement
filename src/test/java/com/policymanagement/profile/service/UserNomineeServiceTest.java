package com.policymanagement.profile.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.policymanagement.profile.dao.UserNomineeDAO;
import com.policymanagement.profile.entity.UserNominee;
import com.policymanagement.profile.entity.Userprofile;
import com.policymanagement.profile.exception.ResourceNotFoundException;
import com.policymanagement.profile.model.UserNomineeDTO;

@ExtendWith(MockitoExtension.class)
public class UserNomineeServiceTest {

    @Mock
    private UserNomineeDAO userNomineeDAO;

    @Mock
    private UserprofileService userprofileService;

    @InjectMocks
    private UserNomineeImplementation userNomineeService;

    @Test
    public void testAddNominee_ValidNominee_SuccessfullyAdded() {
        // Mock the behavior of the userprofileService.getUserByUsername() method
        Userprofile userprofile = new Userprofile();
        when(userprofileService.getUserByUsername("username")).thenReturn(userprofile);

        // Create a UserNomineeDTO object with test data
        UserNomineeDTO nomineeDTO = new UserNomineeDTO();
        nomineeDTO.setFullName("John Doe");
        // Set other properties

        // Call the addNominee() method of the service
        userNomineeService.addNominee("username", nomineeDTO);

        // Verify that the userNomineeDAO.save() method was called with the correct arguments
        verify(userNomineeDAO).save(any(UserNominee.class));
    }

    @Test
    public void testGetNominee_ExistingNominee_ReturnsDTO() {
        // Mock the behavior of the userprofileService.getUserByUsername() method
        Userprofile userprofile = new Userprofile();
        when(userprofileService.getUserByUsername("username")).thenReturn(userprofile);

        // Mock the behavior of the userNomineeDAO.findByUserProfile() method
        UserNominee userNominee = new UserNominee();
        userNominee.setFullName("John Doe");
        // Set other properties

        List<UserNominee> userNomineeList = Collections.singletonList(userNominee);
        when(userNomineeDAO.findByUserProfile(userprofile)).thenReturn(userNomineeList);

        // Call the getNominee() method of the service
        UserNomineeDTO result = userNomineeService.getNominee("username");

        // Assert the result
        assertEquals("John Doe", result.getFullName());
        // Assert other properties
    }

    @Test
    public void testGetNominee_NonExistingNominee_ThrowsException() {
        // Mock the behavior of the userprofileService.getUserByUsername() method
        Userprofile userprofile = new Userprofile();
        when(userprofileService.getUserByUsername("username")).thenReturn(userprofile);

        // Mock the behavior of the userNomineeDAO.findByUserProfile() method
        when(userNomineeDAO.findByUserProfile(userprofile)).thenReturn(Collections.emptyList());

        // Call the getNominee() method of the service and assert that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> userNomineeService.getNominee("username"));
    }
}
