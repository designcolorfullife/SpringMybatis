package com.zhangwei.learning.net.handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import com.alibaba.fastjson.JSON;
import com.zhangwei.learning.net.factory.ActionFactory;
import com.zhangwei.learning.net.vo.TranslationVO;
import com.zhangwei.learning.threadpool.GlobalThreadPool;
import com.zhangwei.learning.utils.LoggerUtils;

/**
 * socket请求的处理Handler
 * 
 * @author Administrator
 * 
 */
public class GlobalSocketRequestHandler implements SocketHandler {

	private ActionFactory actionFactory;

	/**
	 * 从客户端读取请求,渲染出对应的动作并且交给动作工厂处理
	 * 
	 * @param client
	 */
	public void handle(final Socket client) {
		GlobalThreadPool.submitTaskWithoutResult(new Runnable() {

			public void run() {

				TranslationVO translationVO = null;

				InputStream inputStream = null;
				try {
					BufferedReader reader = getReader(client.getInputStream());
					// LoggerUtils.Info(reader.readLine());
					String actString = reader.readLine();
					LoggerUtils.Info("获取到动作字符" + actString);
					translationVO = JSON.parseObject(actString,
							TranslationVO.class);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (translationVO == null) {
					return;
				}
				actionFactory.handle(translationVO, client);
			}
		});
	}

	private BufferedReader getReader(InputStream inputStream) {
		return new BufferedReader(new InputStreamReader(inputStream));
	}

	public void setActionFactory(ActionFactory actionFactory) {
		this.actionFactory = actionFactory;
	}
}
