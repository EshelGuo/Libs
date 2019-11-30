package lib.eshel.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2019/11/27 18:52
 * <br>desc: TODO
 */
public class MMapWriteLogStrategy extends WriteLogStrategy {

	private static final long B = 1L;
	private static final long KB = 1024 * B;
	private static final long MB = 1024 * KB;

	private static final long BUFF_SIZE = 64 * KB;

	private FileChannel mChannel;
	private MappedByteBuffer mBuffer;

	public MMapWriteLogStrategy(File log) {
		super(log);
		try {
			RandomAccessFile raf = new RandomAccessFile(log, "rw");
			mChannel = raf.getChannel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeLog(String content) {
		try {
//			ByteBuffer bb = Charset.forName("utf8").encode(content);
//			mChannel.write(bb);
			if(mBuffer == null){
				mBuffer = mChannel.map(FileChannel.MapMode.READ_WRITE, mChannel.position(), BUFF_SIZE);
			}
			byte[] contentBytes = content.getBytes();
			long free = BUFF_SIZE - mBuffer.position();

			if(contentBytes.length > free){
//				mChannel.write(mBuffer, mChannel.position());
//				mBuffer.compact();
				mBuffer.put(contentBytes, 0, (int) free);
				mBuffer = mChannel.map(FileChannel.MapMode.READ_WRITE, mChannel.position(), BUFF_SIZE);
				mBuffer.put(contentBytes, (int) free, (int) (contentBytes.length - free));
				return;
			}

			mBuffer.put(contentBytes);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
