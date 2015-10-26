package com.zhangwei.learning.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.zhangwei.learning.utils.LoggerUtils;

@Component
public class App implements Runnable {

	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private volatile boolean status;

	public static void main(String[] args) {
		//
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext(
				"spring.xml");
		App app = (App) beanFactory.getBean("app");

	}

	public void stop() {
		status = false;
	}

	@Override
	public void run() {
		while (status) {
			try {
				// 超时时间
				selector.select(1000);
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> key = selectionKeys.iterator();
				while (key.hasNext()) {
					SelectionKey selectionKey = (SelectionKey) key.next();
					key.remove();
					handleKey(selectionKey);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	private void handleKey(SelectionKey key) throws IOException {
		if (key.isValid()) {

		}
	}

	/**
	 * 
	 */
	public App() {
		this(9999);
	}

	public App(int port) {
		// TODO Auto-generated constructor stub
		try {
			// 打开多路复用器
			selector = Selector.open();
			// 打开serversocketchannel
			serverSocketChannel = ServerSocketChannel.open();
			// 非阻塞模型
			serverSocketChannel.configureBlocking(false);
			// 绑定地址
			serverSocketChannel.bind(new InetSocketAddress("localhost", port));
			// 注册到复用器上
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			LoggerUtils.Info("初始化server,监听port", port);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
