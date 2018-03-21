package pro.yuchen.demo.spring_demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 通用AOP
 */
@Aspect
@Component
public class GlobalAspect {
	public static final String POINT = "execution(* pro.yuchen.demo.spring_demo.mapper.*.get(..))";

	@Pointcut(POINT)
	private void pointCutMethod() { }

	// 声明环绕通知
	@Around("pointCutMethod()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object o = pjp.proceed();
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		return o;
	}


}
