package de.fi.webapp.presentation;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/personen")
public class PersonenController {

    @GetMapping(path = "/max", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public PersonDTO getPerson() {
        return new PersonDTO(UUID.randomUUID(), "Max","Mustermann");
    }
}
