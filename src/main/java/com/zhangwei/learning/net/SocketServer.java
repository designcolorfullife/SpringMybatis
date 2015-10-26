package com.zhangwei.learning.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

import org.springframework.stereotype.Component;

import com.zhangwei.learning.enums.ConfigsEnum;
import com.zhangwei.learning.net.handler.SocketHandler;
import com.zhangwei.learning.utils.ConfigMap;
import com.zhangwei.learning.utils.LoggerUtils;

/**
 * 服务端启动socket的类
 */
@Component
public class SocketServer {

	private ServerSocketChannel serverSocketChannel = null;
	private ServerSocket serverSocket = null;

	private ConfigMap configMap;

	private SocketHandler socketHandler;

	private void InitSocket() {
		String portObject = configMap.getConfig(ConfigsEnum.SERVER_SOCKET_PORT);
		if (portObject != null) {
			try {
				serverSocket = new ServerSocket(Integer.valueOf(portObject));
				serverSocketChannel = serverSocket.getChannel();
				LoggerUtils.Info("初始化服务端Socket端口:"
						+ Integer.valueOf(portObject));
			} catch (NumberFormatException e) {
				//
				e.printStackTrace();
			} catch (IOException e) {
				//
				e.printStackTrace();

			}
		}
	}

	public void runserver() {
		try {
			LoggerUtils.Info("服务器开始监听地址" + serverSocket.getInetAddress() + ":"
					+ serverSocket.getLocalPort());
			while (true) {
				Socket newSocket = serverSocket.accept();
				LoggerUtils.Info("建立Socket链接:"
						+ newSocket.getRemoteSocketAddress());
				socketHandler.handle(newSocket);
				LoggerUtils.Info("继续监听下一个请求");
			}
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
	}

	public void setConfigMap(ConfigMap configMap) {
		LoggerUtils.Info("你才我");
		this.configMap = configMap;
	}

	public void setSocketHandler(SocketHandler socketHandler) {
		this.socketHandler = socketHandler;
	}
}
