package de.fi.webapp.service.config;


import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.internal.PersonServiceImpl;
import de.fi.webapp.service.model.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonenConfig {


    @Bean
    @Qualifier("antipathen")
    public List<String> getAntipathen() {
        return List.of("Attila", "Peter", "Paul", "Mary");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> getFruits() {
        return List.of("Banana", "Cherry", "Strawberry", "Raspberry");
    }

    //@Bean
    public PersonService createPersonService(final PersonenRepository repo, final PersonMapper mapper,@Qualifier("antipathen") final List<String> antipathen) {
        return new PersonServiceImpl(repo, mapper, antipathen);
    }
}
