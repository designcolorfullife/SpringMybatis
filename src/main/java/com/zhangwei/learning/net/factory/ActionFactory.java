package com.zhangwei.learning.net.factory;

import java.net.Socket;

import com.zhangwei.learning.net.vo.TranslationVO;

public interface ActionFactory{
	void handle(TranslationVO translationVO, Socket socket);
}
