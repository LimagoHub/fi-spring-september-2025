package de.fi.simplespring.demo;


import de.fi.simplespring.translator.Translator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Named
@Scope("singleton") // Default
@Lazy(false)

//@Scope("prototype") // spring als Fabrik
public class DEmo {



    private final Translator translator;
    //@Value("${DEmo.gruss}")
    private final String gruss ;

    //@Autowired
    public DEmo( final Translator translator, @Value("${DEmo.gruss}") final  String gruss) {
        this.translator = translator;
        this.gruss = gruss;
        System.out.println(translator.translate("Constructor Demo"));
        System.out.println(translator.translate(gruss));
    }

    @PostConstruct
    public void play() {
        System.out.println(translator.translate(gruss));
    }

    @PreDestroy // Nur bei singletons !!!!!!!!
    public void dispose() {
        System.out.println("und tschuess...");
    }
}
