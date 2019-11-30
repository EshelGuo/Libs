package lib.eshel.log;

import javax.swing.filechooser.FileSystemView;

public class JLog {

	private static JLogImpl impl;

	static {
		JLogConfig config = new JLogConfig();

		config.setExtend(new NoneLog())
				.setLogFormat(new JLogFormat())
				.setProjectTag("?")
				.setPackageName("?")
				.setLogSaveDir(FileSystemView.getFileSystemView() .getHomeDirectory())
				.setLogname("JLog.log")
				.openAllLevel();

		impl = new JLogImpl(config);
	}

	public static void error(String TAG, String msg){
		impl.error(TAG, msg);
	}

	public static void warning(String TAG, String msg){
		impl.warning(TAG, msg);
	}

	public static void info(String TAG, String msg){
		impl.info(TAG, msg);
	}

	public static void debug(String TAG, String msg){
		impl.debug(TAG, msg);
	}

	public static void error(String TAG, Throwable throwable){
		impl.error(TAG, throwable);
	}

	public static void printStackTrace(Throwable throwable){
		impl.printStackTrace(throwable);
	}

	public static JLogConfig getConfig(){
		return impl.getLogConfig();
	}

}
