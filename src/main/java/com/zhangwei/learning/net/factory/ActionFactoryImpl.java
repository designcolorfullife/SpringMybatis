package com.zhangwei.learning.net.factory;

import java.net.Socket;
import java.util.Map;

import com.zhangwei.learning.net.processor.ActionProcessor;
import com.zhangwei.learning.net.vo.TranslationVO;
import com.zhangwei.learning.threadpool.GlobalThreadPool;
import com.zhangwei.learning.utils.LoggerUtils;

/**
 * Created by design on 15-10-15.
 */
public class ActionFactoryImpl implements ActionFactory {

	private Map<String, ActionProcessor> actionMap;

	public void handle(final TranslationVO translationVO, final Socket client) {
		GlobalThreadPool.submitTaskWithoutResult(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				LoggerUtils.Info("当前得到的动作" + translationVO + "From:"
						+ client.getRemoteSocketAddress());
				ActionProcessor actionProcessor = actionMap.get(translationVO
						.getActionEnum().getCode());
				actionProcessor.invoke(translationVO, client);
			}
		});
	}

	public void setActionMap(Map<String, ActionProcessor> actionMap) {
		this.actionMap = actionMap;
	}
}
