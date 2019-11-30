package lib.eshel.log;

import java.util.Date;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2019/11/27 17:31
 * <br>desc: TODO
 */
public interface NativeLogFormat {

	String format(Date time, String pid, String tid, String packageName, String level, String TAG, String msg);

	String pid();
	String tid();
}
