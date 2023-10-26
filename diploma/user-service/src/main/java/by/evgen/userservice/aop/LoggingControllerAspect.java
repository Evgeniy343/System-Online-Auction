package by.evgen.userservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LoggingControllerAspect {

    @Pointcut("within(by.evgen.userservice.api.controller.AuctionUserController)")
    public void logAuctionUserController() {
    }

    @Around("logAuctionUserController()")
    public Object logAllMethodCallsAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
        RequestMethod nameOfMethod = null;
        for (RequestMethod requestMethod : annotation.method()) {
            nameOfMethod = requestMethod;
        }
        log.info("Start {} method {}!", nameOfMethod, method.getName());
        log.info("Finished {} method {}!", nameOfMethod, method.getName());
        return joinPoint.proceed();
    }

}
