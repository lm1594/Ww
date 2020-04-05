package lkm.ww.comn.logger;

import org.slf4j.LoggerFactory;

public class Logger {
    public static org.slf4j.Logger getLogger() {
        //return org.slf4j.LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
        
        final Throwable t = new Throwable();
        t.fillInStackTrace();
        return LoggerFactory.getLogger(t.getStackTrace()[1].getClassName());
    }
}
