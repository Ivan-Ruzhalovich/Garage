package Garage.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class userLogging {
    @Around("execution(* Garage.dao.*.*(..))")
    public Object allUsersMethodAroundLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method " + signature.getName() + " starts");
        Object object = joinPoint.proceed();
        System.out.println("Method " + signature.getName() + " ends");
        return object;
    }
}
