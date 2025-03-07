package codegym.c10.com.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Logger {

    // Log trước khi vào bất kỳ phương thức nào trong ComputerService
    @Before("execution(public * codegym.c10.com.service.impl.ComputerService.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.printf("[CMS] START: %s.%s%s%n", className, methodName, args);
    }

    // Log sau khi một method hoàn thành mà không có lỗi
    @AfterReturning(value = "execution(public * codegym.c10.com.service.impl.ComputerService.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.printf("[CMS] SUCCESS: %s.%s -> Kết quả: %s%n", className, methodName, result);
    }
}