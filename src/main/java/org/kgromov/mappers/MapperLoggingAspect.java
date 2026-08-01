package org.kgromov.mappers;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Slf4j
@Component
public class MapperLoggingAspect {

//    @Pointcut("within(org.kgromov.mappers.*)")
    @Pointcut("@within(org.apache.ibatis.annotations.Mapper)")
    public void mappers() {
    }

    @Around("mappers()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String clientMethod = signature.getMethod().getDeclaringClass().getSimpleName() + '#' + signature.getMethod().getName();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        String params = IntStream.range(0, parameterNames.length)
                .boxed()
                .map(i -> parameterNames[i] + "=" + args[i])
                .collect(Collectors.joining(", "));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.debug("Process {} with params = {} took = {} ms", clientMethod, params, stopWatch.lastTaskInfo().getTimeMillis());
        }
    }
}
