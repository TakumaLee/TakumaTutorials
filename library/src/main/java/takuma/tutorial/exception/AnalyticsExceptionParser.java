package takuma.tutorial.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.analytics.tracking.android.ExceptionParser;

public class AnalyticsExceptionParser implements ExceptionParser {

	@Override
	public String getDescription(String p_thread, Throwable p_throwable) {
		return "Thread: " + p_thread + ", Exception: " + ExceptionUtils.getStackTrace(p_throwable);
	}

}
