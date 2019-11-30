package lib.eshel.log;

import javax.swing.TransferHandler;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2019/11/27 16:55
 * <br>desc: TODO
 */
public class JLogImpl {

	static String DEBUG = "DEBUG";
	static String INFO = "INFO";
	static String WARNING = "WARNING";
	static String ERROR = "ERROR";

	private JLogConfig mConfig;
	private NativeLog mNativeLog;
	private ILog extend;

	public JLogImpl(JLogConfig config) {
		mConfig = config;
		mNativeLog = new NativeLog(mConfig);
		extend = mConfig.getExtend();
		if (extend == null) {
			extend = new NoneLog();
		}
	}

	public void printStackTrace(Throwable throwable) {
		if (!mConfig.logIsOpen(LogLevel.WARNING)) {
			return;
		}
		extend.printStackTrace(throwable);
		if (mConfig.isOpenNativeLog()) {
			mNativeLog.printStackTrace(throwable);
		}
	}

	public void error(String TAG, Throwable throwable) {
		if (!mConfig.logIsOpen(LogLevel.ERROR)) {
			return;
		}
		extend.error(TAG, throwable);
		if (mConfig.isOpenNativeLog()) {
			mNativeLog.error(TAG, throwable);
		}
	}

	public void error(String TAG, String msg) {
		if (!mConfig.logIsOpen(LogLevel.ERROR)) {
			return;
		}
		extend.error(TAG, msg);
		if (mConfig.isOpenNativeLog()) {
			mNativeLog.error(TAG, msg);
		}
	}

	public void warning(String TAG, String msg) {
		if (!mConfig.logIsOpen(LogLevel.WARNING)) {
			return;
		}
		extend.warning(TAG, msg);
		if (mConfig.isOpenNativeLog()) {
			mNativeLog.warning(TAG, msg);
		}
	}

	public void info(String TAG, String msg) {
		if (!mConfig.logIsOpen(LogLevel.INFO)) {
			return;
		}
		extend.info(TAG, msg);
		if (mConfig.isOpenNativeLog()) {
			mNativeLog.info(TAG, msg);
		}
	}

	public void debug(String TAG, String msg) {
		if (!mConfig.logIsOpen(LogLevel.DEBUG)) {
			return;
		}
		extend.debug(TAG, msg);
		if (mConfig.isOpenNativeLog()) {
			mNativeLog.debug(TAG, msg);
		}
	}

	public void error(Throwable throwable) {
		error(mConfig.getProjectTag(), throwable);
	}

	public void error(String msg) {
		error(mConfig.getProjectTag(), msg);
	}

	public void warning(String msg) {
		warning(mConfig.getProjectTag(), msg);
	}

	public void info(String msg) {
		info(mConfig.getProjectTag(), msg);
	}

	public void debug(String msg) {
		debug(mConfig.getProjectTag(), msg);
	}

	public JLogConfig getLogConfig() {
		return mConfig;
	}
}
