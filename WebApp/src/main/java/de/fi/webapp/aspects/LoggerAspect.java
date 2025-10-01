package de.fi.webapp.aspects;



import de.fi.webapp.service.PersonenServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
    public void logAdviceAfterReturning(final JoinPoint joinPoint, Object result) {

        logger.warning(String.format("############################# Afterreturning: %s ######################", joinPoint.getSignature().getName()));
        logger.warning(String.format("############################# Result: %s ######################", result.toString()));
    }

    @AfterThrowing(value="execution(public * de.fi.webapp.presentation.v1.PersonenController.*(..))", throwing = "ex")
    public void afterThrowing(final JoinPoint joinPoint, Throwable ex) {

        logger.warning(String.format("############################# Afterreturning: %s ######################", joinPoint.getSignature().getName()));
        logger.warning(String.format("############################# Exception: %s ######################", ex.toString()));
    }

    @Around(value="execution(public * de.fi.webapp.presentation.v1.PersonenController.*(..))")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

}
