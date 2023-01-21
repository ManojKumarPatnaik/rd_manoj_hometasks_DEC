package com.epam.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@EnableAspectJAutoProxy
public class EmployeeServiceAop {

	private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceAop.class);

	@Around("execution(* com.epam.service.EmployeeService.*(..)))")
	public Object employeeServiceAop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		Signature signature = proceedingJoinPoint.getSignature();

		final StopWatch stopWatch = new StopWatch();

		stopWatch.start();
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();

		LOGGER.info("Execution time of " + signature.getDeclaringTypeName() + "." + signature.getName()	 + " :: "
				+ stopWatch.getTotalTimeMillis() + " ms");

		return result;
	}
}
