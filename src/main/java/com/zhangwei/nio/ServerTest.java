package com.zhangwei.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * Created by Administrator on 2015/9/28.
 */
public class ServerTest {
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\BOOTICE.exe");
        //RandomAccessFile�ǿ��Զ��ļ��������λ�ö�ȡ����
        RandomAccessFile randomAccessFile = new RandomAccessFile(f,"rw");
        //�������õ�channel
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(2048);
        int byteRead = channel.read(buf);
        while (byteRead != -1){
            System.out.print("Read " + byteRead + "|" + byteRead);
            buf.flip();

            while (buf.hasRemaining()){
                System.out.print(String.valueOf(buf.get()));
            }
            buf.clear();
            byteRead = channel.read(buf);
        }
        randomAccessFile.close();

    }
}
