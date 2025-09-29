package de.fi.webapp.presentation;


import de.fi.webapp.presentation.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
public class PersonenController {

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
    public ResponseEntity<PersonDTO> findPersonById(@PathVariable  UUID id) {

        if(id.toString().endsWith("7")){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new PersonDTO(id, "Max","Mustermann"));
    }

    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<PersonDTO>> findAll() {


        var liste = List.of(
                new PersonDTO(UUID.randomUUID(), "Max","Mustermann") ,
                new PersonDTO(UUID.randomUUID(), "Erika","Mustermann"),
                new PersonDTO(UUID.randomUUID(), "John","Doe")
        );

        return ResponseEntity.ok(liste);

    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable  UUID id) {

        if(id.toString().endsWith("7")){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();

    }


    @PostMapping(path="")
    public ResponseEntity<Void> saveCustomer(@RequestBody PersonDTO personDTO, UriComponentsBuilder uriBuilder) {

        System.out.println(personDTO.toString() + " wurde erfasst");
        UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(personDTO.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();

    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable UUID id, @RequestBody PersonDTO personDTO) {

        System.out.println(personDTO.toString() + " wurde geaendert");

        return ResponseEntity.ok().build();

    }
}
