package com.policymanagement.profile.service;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.policymanagement.profile.dao.UserprofileDAO;
import com.policymanagement.profile.entity.Userprofile;
import com.policymanagement.profile.model.UserprofileDTO;


class UserprofileServiceTest {

    @Mock
    private UserprofileDAO userprofileRepository;

    @InjectMocks
    private UserprofileImplementation userprofileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_WithValidProfileDTO_ReturnsDTO() {
        // Mock repository behavior
        Userprofile savedUser = new Userprofile();
        savedUser.setUsername("testuser");
        when(userprofileRepository.save(any(Userprofile.class))).thenReturn(savedUser);

        // Create a UserprofileDTO object with test data
        UserprofileDTO profileDTO = new UserprofileDTO();
        profileDTO.setFirstName("John");
        profileDTO.setLastName("Doe");
        profileDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        // Set other properties

        // Call the createUser() method of the service
        UserprofileDTO result = userprofileService.createUser(profileDTO);

        // Verify repository method was called
        verify(userprofileRepository).save(any(Userprofile.class));

        // Assert the result
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        // Assert other properties
    }

    @Test
    void testCreateUser_WithNonIndianNonPassportProfileDTO_ThrowsException() {
        // Create a UserprofileDTO object with test data
        UserprofileDTO profileDTO = new UserprofileDTO();
        profileDTO.setFirstName("John");
        profileDTO.setLastName("Doe");
        profileDTO.setNationality("American");
        profileDTO.setIdProof("Aadhaar");
        // Set other properties

        // Call the createUser() method of the service and assert that it throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> userprofileService.createUser(profileDTO));
    }

    @Test
    void testGetUserByUsername_WithExistingUsername_ReturnsUserprofile() {
        // Mock repository behavior
        Userprofile userprofile = new Userprofile();
        userprofile.setUsername("testuser");
        when(userprofileRepository.findByUsername("testuser")).thenReturn(Optional.of(userprofile));

        // Call the getUserByUsername() method of the service
        Userprofile result = userprofileService.getUserByUsername("testuser");

        // Verify repository method was called
        verify(userprofileRepository).findByUsername("testuser");

        // Assert the result
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void testGetUserByUsername_WithNonExistingUsername_ReturnsNull() {
        // Mock repository behavior
        when(userprofileRepository.findByUsername("nonexistinguser")).thenReturn(Optional.empty());

        // Call the getUserByUsername() method of the service
        Userprofile result = userprofileService.getUserByUsername("nonexistinguser");

        // Verify repository method was called
        verify(userprofileRepository).findByUsername("nonexistinguser");

        // Assert the result
        assertNull(result);
    }
}
