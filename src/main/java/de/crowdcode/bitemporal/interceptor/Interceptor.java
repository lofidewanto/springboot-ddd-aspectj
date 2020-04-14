package de.crowdcode.bitemporal.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Aspect
@ConfigurationProperties("interceptor")
public class Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

	private String message = "Startup";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Around("execution(* *(..)) && !within(de.crowdcode.bitemporal.interceptor.Interceptor)"
			+ " && (within(org.springframework.context.annotation.Condition+) || within(de.crowdcode..*))")
	public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		logger.error("AspectJ intercept: " + joinPoint.toShortString() + ": " + result);
		return result;
	}

	@Around("execution(* *(..)) && within(de.crowdcode..*) && !within(de.crowdcode.bitemporal.interceptor.Interceptor+)")
	public Object stack(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.error("AspectJ stack: " + joinPoint.toShortString());
		return joinPoint.proceed();
	}

	@EventListener
	public void started(ContextRefreshedEvent event) {
		logger.error("AspectJ started: " + message + ": " + event);
	}

}
