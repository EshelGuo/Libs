package lib.eshel.log;

import java.io.File;

public abstract class WriteLogStrategy{

	public WriteLogStrategy(File log) {}

	abstract void writeLog(String content);

	public interface Factory{
		WriteLogStrategy create(File log);
	}
}