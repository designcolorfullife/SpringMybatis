package com.zhangwei.learning.net.processor;

import java.net.Socket;

import com.zhangwei.learning.net.vo.TranslationVO;

public interface ActionProcessor {
	void invoke(TranslationVO translationVO, Socket client);
}
