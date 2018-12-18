package com.test.common.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggingAdvice implements MethodInterceptor {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		String className = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		if (logger.isDebugEnabled()) {
			logger.debug("Class: " + className + "." + methodName + "() start");
			Object[] args = invocation.getArguments();
			if ((args != null) && (args.length > 0)) {
				for (int i = 0; i < args.length; i++) {
					logger.debug("Argument[" + i + "]: " + args[i]);
				}
			}
		}
		Object returnValue = invocation.proceed();

		if (logger.isDebugEnabled()) {
			logger.debug("Class: " + className + "." + methodName + "() end");
		}
		return returnValue;
	}
}