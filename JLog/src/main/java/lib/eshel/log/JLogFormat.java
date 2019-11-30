package lib.eshel.log;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2019/11/27 17:32
 * <br>desc: TODO
 */
public class JLogFormat implements NativeLogFormat {

	private SimpleDateFormat format = new SimpleDateFormat("MM-dd hh:mm:ss.SSS", Locale.getDefault());

	@Override
	public String format(Date time, String pid, String tid, String packageName, String level, String TAG, String msg) {
		return String.format(Locale.getDefault(), "%s %s-%s/%s %s/%s: %s\n",
				format.format(time),
				pid,
				tid,
				packageName,
				level,
				TAG,
				msg
		);
	}

	@Override
	public String pid() {
		return ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
	}

	@Override
	public String tid() {
		return String.valueOf(Thread.currentThread().getId());
	}
}
