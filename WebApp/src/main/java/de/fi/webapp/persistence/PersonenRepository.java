package de.fi.webapp.persistence;

import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.persistence.entity.TinyPerson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PersonenRepository extends CrudRepository<PersonEntity, UUID> {

    Iterable<PersonEntity> findByVorname(String vorname);


    @Query("select new de.fi.webapp.persistence.entity.TinyPerson (p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> findTinies();
}
