package kr.co.seoulit.common.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.dao.DataAccessException;

public class CommonAdvice {
	protected final Log logger = LogFactory.getLog(getClass());

	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		if (logger.isDebugEnabled()) {
			logger.debug(className + "." + methodName + "() 시작");
		}
		Object[] args = joinPoint.getArgs();
		if ((args != null) && (args.length > 0)) {
			for (int i = 0; i < args.length; i++) {
				logger.debug("Argument[" + i + "] : " + args[i]);
			}
		}

		Object retVal = joinPoint.proceed();
		if (logger.isDebugEnabled()) {
			logger.debug(className + "." + methodName + "() 종료");
		}
		return retVal;
	}

	public void afterThrowing(DataAccessException ex) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.debug("Caught : " + ex.getClass().getName());
		}
		if (logger.isErrorEnabled()) {
			logger.fatal(ex.getMessage());
		}
		throw ex;
	}
}
