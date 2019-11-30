package lib.eshel.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

import static lib.eshel.log.JLogImpl.DEBUG;
import static lib.eshel.log.JLogImpl.ERROR;
import static lib.eshel.log.JLogImpl.INFO;
import static lib.eshel.log.JLogImpl.WARNING;

public class NativeLog implements ILog {

	private NativeLogImpl mImpl;

	public NativeLog(JLogConfig logConfig) {
		mImpl = new NativeLogImpl(logConfig);
	}

	public void printStackTrace(Throwable throwable) {
		if (throwable != null) {
			mImpl.saveLogToNative(WARNING, "System.err", getStackTraceString(throwable));
		}
	}

	public String getStackTraceString(Throwable tr) {
		if (tr == null) {
			return "";
		}

		// This is to reduce the amount of log spew that apps do in the non-error
		// condition of the network being unavailable.
		Throwable t = tr;
		while (t != null) {
			if (t instanceof UnknownHostException) {
				return "";
			}
			t = t.getCause();
		}

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, false);
		tr.printStackTrace(pw);
		pw.flush();
		return sw.toString();
	}

	@Override
	public void error(String TAG, String msg) {
		mImpl.saveLogToNative(ERROR, TAG, msg);
	}

	@Override
	public void warning(String TAG, String msg) {
		mImpl.saveLogToNative(WARNING, TAG, msg);
	}

	@Override
	public void info(String TAG, String msg) {
		mImpl.saveLogToNative(INFO, TAG, msg);
	}

	@Override
	public void debug(String TAG, String msg) {
		mImpl.saveLogToNative(DEBUG, TAG, msg);
	}

	@Override
	public void error(String TAG, Throwable throwable) {
		mImpl.saveLogToNative(ERROR, TAG, getStackTraceString(throwable));
	}
}