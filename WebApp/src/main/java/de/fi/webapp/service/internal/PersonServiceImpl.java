package de.fi.webapp.service.internal;

import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.model.Person;
import de.fi.webapp.service.model.mapper.PersonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(rollbackFor = PersonenServiceException.class,isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class PersonServiceImpl implements PersonService {


    private final PersonenRepository repository;
    private final PersonMapper mapper;

    public PersonServiceImpl(final PersonenRepository repository, final PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /*
        person == null -> PSE
        vorname == null oder zu kurz ->PSE
        nachname dito

        Attila -> PSE

        exception in underlying service ->PSE

        happy day -> Person to Repo

     */
    @Override
    public void speichern(Person person) throws PersonenServiceException {
        try {
            if(person == null)
                throw new PersonenServiceException("Person darf nicht null sein!");

            if(person.getVorname() == null || person.getVorname().length() <2)
                throw new PersonenServiceException("Vorname zu kurz!");

            if(person.getNachname() == null || person.getNachname().length() <2)
                throw new PersonenServiceException("Namename zu kurz!");

            if(person.getVorname().equalsIgnoreCase("Attila"))
                throw new PersonenServiceException("Unerwuenschte Person");

            repository.save(mapper.convert(person));
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Ein Fehler ist aufgetreten!", e);
        }
    }

    @Override
    public boolean aendern(final Person person) throws PersonenServiceException {

        if(! repository.existsById(person.getId())) return false;
        speichern(person);
        return true;
    }

    @Override
    public boolean loesche(final UUID id) throws PersonenServiceException {
        try {
            if(! repository.existsById(id)) return false;
            repository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps",e);
        }
    }

    @Transactional(rollbackFor = PersonenServiceException.class,isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Optional<Person> findeNachId(final UUID id) throws PersonenServiceException {
        try {
            return repository.findById(id).map(mapper::convert);
        } catch (Exception e) {
            throw new PersonenServiceException("Upps",e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return mapper.convert(repository.findAll());
        } catch (Exception e) {
            throw new PersonenServiceException("Upps", e);
        }
    }
}
