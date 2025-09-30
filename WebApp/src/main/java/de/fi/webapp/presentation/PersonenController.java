package de.fi.webapp.presentation;


import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.presentation.dto.PersonDTO;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.PersonenServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
@RequestScope
public class PersonenController {

    private final PersonDTOMapper mapper;
    private final PersonService service;

    public PersonenController(final PersonDTOMapper mapper, final PersonService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @Operation(summary = "Liefert eine Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})


    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> findPersonById(@PathVariable  UUID id) throws PersonenServiceException {

        return ResponseEntity.of(service.findeNachId(id).map(mapper::convert));
    }

    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<PersonDTO>> findAll(
            @RequestParam(required = false, defaultValue = "Fritz") String vorname,
            @RequestParam(required = false, defaultValue = "Schmitt")String nachname
    ) throws PersonenServiceException {

        System.out.printf("Vorname = %s und Nachname, %s\n", vorname, nachname);


        return ResponseEntity.ok(mapper.convert(service.findeAlle()));

    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable  UUID id) throws PersonenServiceException{

       if(service.loesche(id)) return ResponseEntity.ok().build();
       return ResponseEntity.notFound().build();

    }


    @PostMapping(path="", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody @Valid PersonDTO personDTO, UriComponentsBuilder uriBuilder)throws PersonenServiceException {

        service.speichern(mapper.convert(personDTO));
        UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(personDTO.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();

    }

    @PutMapping(path="/{id}",consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable UUID id, @Valid @RequestBody PersonDTO personDTO) throws PersonenServiceException{

        if(service.aendern(mapper.convert(personDTO)))return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();

    }
}
