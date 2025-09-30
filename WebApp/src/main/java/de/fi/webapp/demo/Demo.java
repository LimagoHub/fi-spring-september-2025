package de.fi.webapp.demo;



import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.persistence.entity.PersonEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Component
public class Demo {

    private final PersonenRepository repository;

    public Demo(final PersonenRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void play() {
        /*PersonEntity person = new PersonEntity(UUID.randomUUID(), "John", "Rambo");
        repository.save(person);
        person = new PersonEntity(UUID.randomUUID(), "John", "McClain");
        repository.save(person);
        person = new PersonEntity(UUID.randomUUID(), "John", "Wick");
        repository.save(person);
        person = new PersonEntity(UUID.randomUUID(), "John", "Wayne");
        repository.save(person);
        person = new PersonEntity(UUID.randomUUID(), "John Boy", "Walton");
        repository.save(person);
*/

        repository.findByVorname("John").forEach(System.out::println);
    }
}
