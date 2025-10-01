package de.fi.webapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(public * de.fi.webapp.presentation.v1.PersonenController.*(..))")
    public void personenQueryControllerMethods(){}

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceMethods(){}

    @Pointcut("@within(de.fi.webapp.aspects.Dozent)")
    public void dozentMethods(){}
}
