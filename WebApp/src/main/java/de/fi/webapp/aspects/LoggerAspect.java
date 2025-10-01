package de.fi.webapp.aspects;



import de.fi.webapp.service.PersonenServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;


@Aspect
@Component
public class LoggerAspect {

    private static Logger logger = Logger.getLogger(LoggerAspect.class.getName());


    @Pointcut("execution(public * de.fi.webapp.presentation.v1.PersonenController.*(..))")
    public void personenQueryControllerMethods(){}


    @Before("Pointcuts.personenQueryControllerMethods()")
    public void before(final JoinPoint joinPoint) {
        logger.warning( String.format(
                "##################### Methode  %s wurde aufgerufen ########################"
                , joinPoint.getSignature().getName()));
    }

    @AfterReturning(value = "Pointcuts.personenQueryControllerMethods()", returning = "result")
    public void logAdviceAfterReturning(final JoinPoint joinPoint, Object result) {

        logger.warning(String.format("############################# Afterreturning: %s ######################", joinPoint.getSignature().getName()));
        logger.warning(String.format("############################# Result: %s ######################", result.toString()));
    }

    @AfterThrowing(value="execution(public * de.fi.webapp.presentation.v1.PersonenController.*(..))", throwing = "ex")
    public void afterThrowing(final JoinPoint joinPoint, Throwable ex) {

        logger.warning(String.format("############################# Afterreturning: %s ######################", joinPoint.getSignature().getName()));
        logger.warning(String.format("############################# Exception: %s ######################", ex.toString()));
    }

    @Around(value="de.fi.webapp.aspects.Pointcuts.dozentMethods()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {

        Instant start = Instant.now();
        var result = joinPoint.proceed();
        Instant end = Instant.now();
        logger.warning(String.format("Duration = %s ms" , Duration.between(start, end).toMillis() ));

        return result;
    }

}
