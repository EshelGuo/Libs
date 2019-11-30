package lib.eshel.log;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2019/11/27 17:07
 * <br>desc: none log
 */
public class NoneLog implements ILog{
	@Override
	public void error(String TAG, String msg) {

	}

	@Override
	public void warning(String TAG, String msg) {

	}

	@Override
	public void info(String TAG, String msg) {

	}

	@Override
	public void debug(String TAG, String msg) {

	}

	@Override
	public void error(String TAG, Throwable throwable) {

	}

	@Override
	public void printStackTrace(Throwable throwable) {

	}
}
