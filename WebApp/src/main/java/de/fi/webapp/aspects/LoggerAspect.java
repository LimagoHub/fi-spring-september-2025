package de.fi.webapp.aspects;



import de.fi.webapp.service.PersonenServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Aspect
@Component
public class LoggerAspect {

    private Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Before("execution(public * de.fi.webapp.presentation.v1.PersonenController.*(..))")
    public void before(final JoinPoint joinPoint) {
        logger.warning( String.format(
                "##################### Methode  %s wurde aufgerufen ########################"
                , joinPoint.getSignature().getName()));
    }

    @AfterReturning(value = "execution(public * de.fi.webapp.presentation.v1.PersonenController.*(..))", returning = "result")
    public void logAdviceAfterReturning(final JoinPoint joinPoint, Object result) throws PersonenServiceException {

        logger.warning(String.format("############################# Afterreturning: %s ######################", joinPoint.getSignature().getName()));
        logger.warning(String.format("############################# Result: %s ######################", result.toString()));
    }

}
