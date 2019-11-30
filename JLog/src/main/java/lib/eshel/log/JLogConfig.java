package lib.eshel.log;

import java.io.File;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2019/11/27 16:57
 * <br>desc: TODO
 */
public class JLogConfig {

	private String projectTag = "JLog";
	private boolean openNativeLog = false;
	private ILog extend;
	private NativeLogFormat mLogFormat;
	private String packageName;
	private File saveDir;
	private String logname;
	private WriteLogStrategy.Factory nativeLogStrategyFactory;

	private int minLevel;
	private int maxLevel;

	public JLogConfig setProjectTag(String projectTag) {
		this.projectTag = projectTag;
		return this;
	}

	public JLogConfig openNativeLog(boolean openNativeLog) {
		this.openNativeLog = openNativeLog;
		return this;
	}

	public JLogConfig setExtend(ILog extend) {
		this.extend = extend;
		return this;
	}

	public JLogConfig setLogFormat(NativeLogFormat logFormat) {
		mLogFormat = logFormat;
		return this;
	}

	public String getProjectTag() {
		return projectTag;
	}

	public boolean isOpenNativeLog() {
		return openNativeLog;
	}

	public ILog getExtend() {
		return extend;
	}

	public NativeLogFormat getLogFormat() {
		if(mLogFormat == null){
			mLogFormat = new JLogFormat();
		}
		return mLogFormat;
	}

	public JLogConfig setLogLevel(int minLevel, int maxLevel){
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		return this;
	}

	public JLogConfig closeLog(){
		return setLogLevel(LogLevel.NONE, LogLevel.NONE);
	}

	public JLogConfig openAllLevel(){
		return setLogLevel(LogLevel.DEBUG, LogLevel.ERROR);
	}

	public int getMinLevel() {
		return minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public boolean logIsOpen(int level){
		return level >= getMinLevel() && level <= getMaxLevel();
	}

	public String getPackageName() {
		return packageName;
	}

	public JLogConfig setPackageName(String packageName) {
		this.packageName = packageName;
		return this;
	}

	public JLogConfig setLevelName(int level, String levelName){
		switch (level){
			case LogLevel.DEBUG:
				JLogImpl.DEBUG = levelName;
				break;
			case LogLevel.INFO:
				JLogImpl.INFO = levelName;
				break;
			case LogLevel.WARNING:
				JLogImpl.WARNING = levelName;
				break;
			case LogLevel.ERROR:
				JLogImpl.ERROR = levelName;
				break;
			default:
				throw new IllegalArgumentException("No such log level");
		}
		return this;
	}

	public JLogConfig setLogSaveDir(File saveDir) {
		this.saveDir = saveDir;
		return this;
	}

	public JLogConfig setLogname(String logname) {
		this.logname = logname;
		return this;
	}

	public String getLogname() {
		return logname;
	}

	public File getLogSaveDir() {
		return saveDir;
	}

	public JLogConfig setNativeLogStrategyFactory(WriteLogStrategy.Factory nativeLogStrategyFactory) {
		this.nativeLogStrategyFactory = nativeLogStrategyFactory;
		return this;
	}

	public WriteLogStrategy.Factory getNativeLogStrategyFactory() {
		return nativeLogStrategyFactory;
	}
}
