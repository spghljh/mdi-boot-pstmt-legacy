package kr.co.mdi.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionLogger {

    private static final Logger log = LoggerFactory.getLogger(ExecutionLogger.class);

    @Pointcut("execution(* kr.co.mdi..*(..)) && !execution(* kr.co.mdi.common.config.TraceIdFilter.*(..))")
    public void applicationPackagePointcut() {}

    @Before("applicationPackagePointcut()")
    public void logMethodEntry(JoinPoint joinPoint) {
        String layer = detectLayer(joinPoint.getSignature().getDeclaringTypeName());
        log.info("▶ {}: {}", layer, joinPoint.getSignature().toShortString());
    }

    @After("applicationPackagePointcut()")
    public void logMethodExit(JoinPoint joinPoint) {
        String layer = detectLayer(joinPoint.getSignature().getDeclaringTypeName());
        log.info("◁ {}: {}", layer, joinPoint.getSignature().toShortString());
    }

    private String detectLayer(String className) {
        if (className.contains("Controller")) return "Controller";
        if (className.contains("Service")) return "Service";
        if (className.contains("Repository") || className.contains("Dao")) return "DAO";
        return "App";
    }
}


