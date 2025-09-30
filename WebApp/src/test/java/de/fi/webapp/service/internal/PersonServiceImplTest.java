package de.fi.webapp.service.internal;

import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.model.mapper.PersonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    PersonServiceImpl objectUnderTest;

    @Mock
    PersonMapper personMapperMock;

    @Mock
    PersonenRepository personenRepositoryMock;

    @Test
    void speichern_personNull_throwsPersonenServiceException() throws Exception {
        PersonenServiceException ex = Assertions.assertThrows(PersonenServiceException.class,()-> objectUnderTest.speichern(null));
        assertEquals("Person darf nicht null sein!", ex.getMessage());
    }
}