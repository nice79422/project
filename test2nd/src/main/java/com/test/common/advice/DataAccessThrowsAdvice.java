package com.test.common.advice;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.dao.DataAccessException;

public class DataAccessThrowsAdvice implements ThrowsAdvice {

	protected final Log logger = LogFactory.getLog(this.getClass());

	public void afterThrowing(DataAccessException ex) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.debug("DataAccessException afterThrowing START");
			logger.debug("Caught: " + ex.getClass().getName());
		}

			logger.fatal(ex.getMessage());


		if (logger.isDebugEnabled()) {
			logger.debug("DataAccessException afterThrowing END");
		}
		throw ex;
	}

	public void afterThrowing(Exception ex) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.debug("Exception afterThrowing START");
			logger.debug("Caught: " + ex.getClass().getName());
		}

			logger.fatal(ex.getMessage());

		if (logger.isDebugEnabled()) {
			logger.debug("Exception afterThrowing END");
		}
		throw ex;
	}
}
