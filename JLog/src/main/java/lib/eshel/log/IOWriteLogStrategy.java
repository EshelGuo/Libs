package lib.eshel.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2019/11/27 18:51
 * <br>desc: TODO
 */
public class IOWriteLogStrategy extends WriteLogStrategy{

	private BufferedWriter sWriter;

	public IOWriteLogStrategy(File log) {
		super(log);
		try {
			sWriter = new BufferedWriter(new FileWriter(log, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeLog(String content) {
		try {
			sWriter.write(content);
			sWriter.flush();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
