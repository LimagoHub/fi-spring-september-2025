package de.fi.simplespring.demo;


import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // Default
@Lazy(false)

//@Scope("prototype") // spring als Fabrik
public class DEmo {

    public DEmo() {

        System.out.println("DEmo constructor");
    }
}
