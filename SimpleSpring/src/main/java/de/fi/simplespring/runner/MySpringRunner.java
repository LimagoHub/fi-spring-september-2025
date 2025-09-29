package de.fi.simplespring.runner;

import de.fi.simplespring.math.Calculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MySpringRunner implements CommandLineRunner {

    private final Calculator calculator;

    public MySpringRunner(@Qualifier("secure") Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println(calculator.add(3,4));
    }
}
