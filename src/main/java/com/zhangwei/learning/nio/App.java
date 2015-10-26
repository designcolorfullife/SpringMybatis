package com.zhangwei.learning.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class App {

	public static void main(String[] args) throws Exception {

	}

}

class NIO implements Runnable {
	private volatile boolean status = true;
	private ServerSocketChannel serverSocketChannel = null;
	private Selector selector = null;

	public NIO() throws IOException {
		// 打开ServerSocketChannel
		serverSocketChannel = ServerSocketChannel.open();
		// 打开多路复用器
		selector = Selector.open();
		// 设置非阻塞
		serverSocketChannel.configureBlocking(false);
		// 绑定地址
		serverSocketChannel.socket()
				.bind(new InetSocketAddress(InetAddress.getByName("localhost"),
						8080));
		// 将Channel注册到Selector上
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	public void run() {
		while (status) {
			try {
				// 轮训
				selector.select(3000);
				// 获取轮训到的SelectorKeys
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				while (iterator.hasNext()) {

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}