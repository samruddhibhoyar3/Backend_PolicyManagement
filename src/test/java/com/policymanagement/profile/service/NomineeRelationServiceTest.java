package com.policymanagement.profile.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.policymanagement.profile.dao.NomineeRelationDAO;
import com.policymanagement.profile.entity.NomineeRelation;
import com.policymanagement.profile.model.NomineeRelationDTO;

@RunWith(MockitoJUnitRunner.class)
public class NomineeRelationServiceTest {

    @Mock
    private NomineeRelationDAO nomineeRelationRepository;

    @InjectMocks
    private NomineeRelationImplementation nomineeRelationService;

    @Test
    public void testGetNomineeTypes_ReturnsListOfTypes() {
        // Create a list of NomineeRelation entities for testing
        List<NomineeRelation> nomineeRelations = Arrays.asList(
                new NomineeRelation(1L, "Parent"),
                new NomineeRelation(2L, "Child"),
                new NomineeRelation(3L, "Sibling")
        );

        // Mock the behavior of the nomineeRelationRepository.findAll() method
        when(nomineeRelationRepository.findAll()).thenReturn(nomineeRelations);

        // Call the getNomineeTypes() method of the service
        List<NomineeRelationDTO> result = nomineeRelationService.getNomineeTypes();

        // Assert the result
        assertEquals(3, result.size());
        // Assert other properties if needed
    }

    @Test
    public void testSeedNomineeTypes_RepositoryEmpty_TypesSeeded() {
        // Mock the behavior of the nomineeRelationRepository.count() method
        when(nomineeRelationRepository.count()).thenReturn(0L);

        // Call the seedNomineeTypes() method of the service
        nomineeRelationService.seedNomineeTypes();

        // Verify that the nomineeRelationRepository.save() method was called the expected number of times
        verify(nomineeRelationRepository, times(6)).save(any(NomineeRelation.class));
    }

    @Test
    public void testSeedNomineeTypes_RepositoryNotEmpty_TypesNotSeeded() {
        // Mock the behavior of the nomineeRelationRepository.count() method
        when(nomineeRelationRepository.count()).thenReturn(3L);

        // Call the seedNomineeTypes() method of the service
        nomineeRelationService.seedNomineeTypes();

        // Verify that the nomineeRelationRepository.save() method was not called
        verify(nomineeRelationRepository, never()).save(any(NomineeRelation.class));
    }

    // Add more test cases for the remaining methods if needed
}
