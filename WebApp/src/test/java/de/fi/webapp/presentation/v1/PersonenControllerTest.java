package de.fi.webapp.presentation.v1;

import de.fi.webapp.presentation.dto.PersonDTO;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private PersonService personenServiceMock;

    @Test
    void findByIdTest() throws Exception {
        final Optional<Person> optionalPerson = Optional.of(new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"),"John","Doe"));

        // Recordmode
        when(personenServiceMock.findeNachId(any(UUID.class))).thenReturn(optionalPerson);
        // Replay

        var result = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);
        assertEquals("John", result.getVorname() );
    }

    @Test
    void test2() throws Exception {
        final Optional<Person> optionalPerson = Optional.of(new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"),"John","Doe"));

        // Recordmode
        when(personenServiceMock.findeNachId(any(UUID.class))).thenReturn(optionalPerson);
        // Replay

        var result = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        System.out.println(result);
        //assertEquals("John", result.getVorname() );
    }

    @Test
    void test3() throws Exception {
        final Optional<Person> optionalPerson = Optional.of(new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"),"John","Doe"));

        // Recordmode
        when(personenServiceMock.findeNachId(any(UUID.class))).thenReturn(optionalPerson);
        // Replay

        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);

        var result = entity.getBody();
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("John", result.getVorname() );
    }

    @Test
    void test4() throws Exception {
        final Optional<Person> optionalPerson = Optional.empty();

        // Recordmode
        when(personenServiceMock.findeNachId(any(UUID.class))).thenReturn(optionalPerson);
        // Replay

        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);

        var result = entity.getBody();
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());

    }

    @Test
    void test5() throws Exception {
        var personen = List.of(
                new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"),"John","Doe"),
                new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"),"John","Rambo"),
                new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"),"John","Wick")
        );


        PersonDTO nurZurDemo = new PersonDTO(UUID.randomUUID(),"John","Wick");
        HttpEntity<PersonDTO> entityToUpload = new HttpEntity<>(nurZurDemo);

        when(personenServiceMock.findeAlle()).thenReturn(personen);
        var entity = restTemplate.exchange("/v1/personen", HttpMethod.GET, entityToUpload,new ParameterizedTypeReference<List<PersonDTO>>() { });
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        var result = entity.getBody();
        assertEquals(result.size() , 3);
    }



}