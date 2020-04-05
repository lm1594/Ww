package lkm.ww.comn.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log 유틸
 * @author lkm
 * @since 2020. 04. 02
 */
public class LogUtil implements Serializable {

	private static final long serialVersionUID = -4788521559600531291L;
	
	private transient Logger logger;
	protected final String loggerName;

	protected LogUtil() {
		this.loggerName = getClass().getName();
	}

	protected LogUtil(String loggerName) {
		this.loggerName = loggerName;
	}

	public boolean isTraceEnabled() {
		initLogger();
		return logger.isTraceEnabled();
	}

	public void trace(String message) {
		initLogger();
		logger.trace(message);
	}

	public void trace(String message, Object... objects) {
		initLogger();
		logger.trace(message, objects);
	}

	public void trace(String message, Throwable t) {
		initLogger();
		logger.trace(message, t);
	}

	public boolean isDebugEnabled() {
		initLogger();
		return logger.isDebugEnabled();
	}

	public void debug(String message) {
		initLogger();
		logger.debug(message);
	}

	public void debug(String message, Object... objects) {
		initLogger();
		logger.debug(message, objects);
	}

	public void debug(String message, Throwable t) {
		initLogger();
		logger.debug(message, t);
	}

	public boolean isInfoEnabled() {
		initLogger();
		return logger.isInfoEnabled();
	}

	public void info(String message) {
		initLogger();
		logger.info(message);
	}

	public void info(String message, Object... objects) {
		initLogger();
		logger.info(message, objects);
	}

	public void info(String message, Throwable t) {
		initLogger();
		logger.info(message, t);
	}

	public boolean isWarnEnabled() {
		initLogger();
		return logger.isWarnEnabled();
	}

	public void warn(String message) {
		initLogger();
		logger.warn(message);
	}

	public void warn(String message, Object... objects) {
		initLogger();
		logger.warn(message, objects);
	}

	public void warn(String message, Throwable t) {
		initLogger();
		logger.warn(message, t);
	}

	public boolean isErrorEnabled() {
		initLogger();
		return logger.isErrorEnabled();
	}

	public void error(String message) {
		initLogger();
		logger.error(message);
	}

	public void error(String message, Object... objects) {
		initLogger();
		logger.error(message, objects);
	}

	public void error(String message, Throwable t) {
		initLogger();
		logger.error(message, t);
	}

	private void initLogger() {
		if (logger == null) {
			logger = LoggerFactory.getLogger(loggerName);
		}
	}
}