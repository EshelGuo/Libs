package lib.eshel.log;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2019/11/27 17:04
 * <br>desc: 用于扩展打印日志
 */
public interface ILog {

	void error(String TAG, String msg);
	void warning(String TAG, String msg);
	void info(String TAG, String msg);
	void debug(String TAG, String msg);
	void error(String TAG, Throwable throwable);
	void printStackTrace(Throwable throwable);
}
