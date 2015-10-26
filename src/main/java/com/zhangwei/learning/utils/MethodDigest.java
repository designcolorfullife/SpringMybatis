package com.zhangwei.learning.utils;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodDigest implements MethodInterceptor {

	/**
	 * 
	 * 第一个*号：表示返回类型，*号表示所有的类型。
	 * 3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
	 * 4、第二个*号：表示类名，*号表示所有的类。
	 * 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
	 * 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.zhangwei.learning..*.*(..))")
	public Object invoke(ProceedingJoinPoint point) throws Throwable {
		return execute(point.getArgs(), point.getTarget().getClass()
				.getSimpleName(), point.getSignature().getName(),
				new CommonExcutor(point));
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		return execute(invocation.getArguments(), invocation.getThis()
				.getClass().getSimpleName(), invocation.getMethod().getName(),
				new CommonExcutor(invocation));
	}

	private Object execute(Object[] args, String className, String methodName,
			CommonExcutor commonExcutor) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("类名:").append(className).append("方法名:")
				.append(methodName);
		if (args == null) {
			stringBuilder.append("没有入参,");
		} else {
			stringBuilder.append("入参:{");
			for (Object aObject : args) {
				stringBuilder.append("Class:")
						.append(aObject.getClass().getSimpleName())
						.append(".class值:").append(aObject.toString());
			}
			stringBuilder.append("}");
		}
		stringBuilder.append("执行结果:");
		Object result = null;
		long start = System.nanoTime();
		try {
			result = commonExcutor.execute();
			stringBuilder.append(result + "");
		} catch (Exception e) {
			stringBuilder.append("Exception:").append(e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			long end = System.nanoTime();
			stringBuilder.append("执行时间:").append(end - start);
			LoggerUtils.Info(stringBuilder.toString());
		}
		return result;
	}
}

class CommonExcutor {
	private ProceedingJoinPoint point;
	private MethodInvocation invocation;

	public Object execute() throws Throwable {
		if (point != null) {
			return point.proceed();
		}
		if (invocation != null) {
			return invocation.proceed();
		}
		return null;
	}

	public CommonExcutor(ProceedingJoinPoint point) {
		this.point = point;
	}

	public CommonExcutor(MethodInvocation invocation) {
		this.invocation = invocation;
	}
}
