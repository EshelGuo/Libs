import java.io.File;
import java.util.Locale;

import lib.eshel.log.IOWriteLogStrategy;
import lib.eshel.log.JLog;
import lib.eshel.log.MMapWriteLogStrategy;
import lib.eshel.log.WriteLogStrategy;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2019/11/27 19:39
 * <br>desc: TODO
 */
public class Test {

	public static void main(String args[]){
		JLog.getConfig().openNativeLog(true);
		JLog.getConfig().setNativeLogStrategyFactory(new WriteLogStrategy.Factory() {
			@Override
			public WriteLogStrategy create(File log) {
				return new MMapWriteLogStrategy(log);
			}
		});
		long ago = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			JLog.debug("TAG", String.format(Locale.getDefault(), "%s-%d","createBy guoshiwen", i));
		}

		long time = System.currentTimeMillis() - ago;
		System.out.println("NIO耗时: "+time);
	}
}
