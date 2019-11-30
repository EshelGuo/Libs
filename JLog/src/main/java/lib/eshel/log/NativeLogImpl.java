package lib.eshel.log;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2019/11/27 17:27
 * <br>desc: TODO
 */
public class NativeLogImpl {

	private JLogConfig mLogConfig;
	private WriteLogStrategy mLogStrategy;

	public NativeLogImpl(JLogConfig logConfig) {
		mLogConfig = logConfig;
	}

	public void saveLogToNative(String level, String TAG, String msg) {
		if (level == null) {
			level = "known";
		}

		if (TAG == null) {
			TAG = mLogConfig.getProjectTag();
		}

		if (msg == null) {
			msg = "null";
		}

		try {
			saveLogToNativeInternal(level, TAG, msg);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void saveLogToNativeInternal(String level, String tag, String msg) {
		NativeLogFormat logFormat = mLogConfig.getLogFormat();
		String content = logFormat.format(new Date(), logFormat.pid(),
				logFormat.tid(), mLogConfig.getPackageName(), level, tag, msg);

		if (mLogStrategy == null) {
			File dir = mLogConfig.getLogSaveDir();
			if (!dir.exists() || !dir.isDirectory()) {
				//noinspection ResultOfMethodCallIgnored
				dir.mkdirs();
			}

			File log = new File(dir, mLogConfig.getLogname());
			if (!log.exists() || !log.isFile()) {
				try {
					//noinspection ResultOfMethodCallIgnored
					log.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			WriteLogStrategy.Factory factory = mLogConfig.getNativeLogStrategyFactory();
			if (factory != null) {
				mLogStrategy = factory.create(log);
			}

			if (mLogStrategy == null) {
				mLogStrategy = new MMapWriteLogStrategy(log);
			}
		}
		mLogStrategy.writeLog(content);
	}
}
