package lkm.ww.comn.logger;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
import net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator;
import net.sf.log4jdbc.sql.resultsetcollector.ResultSetCollector;

@Slf4j
public class CfdSpyLogDelegator extends Slf4jSpyLogDelegator {

	/**
	 * Logger that shows the forward scrolled result sets in a table
	 */
	private final Logger resultSetTableLogger = LoggerFactory.getLogger("jdbc.resultsettable");

	@Override
	public boolean isResultSetCollectionEnabled() {
		return resultSetTableLogger.isInfoEnabled();
	}

	@Override
	public boolean isResultSetCollectionEnabledWithUnreadValueFillIn() {
		return resultSetTableLogger.isDebugEnabled();
	}

	@Override
	public void resultSetCollected(ResultSetCollector resultSetCollector) {
		try {
			String resultsToPrint = new ResultSetCollectorPrinter().getResultSetToPrint(resultSetCollector);
			resultSetTableLogger.info(resultsToPrint);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
	}

}
