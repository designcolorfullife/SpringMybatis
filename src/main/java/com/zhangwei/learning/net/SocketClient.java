package com.zhangwei.learning.net;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.alibaba.fastjson.JSON;
import com.zhangwei.learning.enums.ActionEnum;
import com.zhangwei.learning.net.vo.TranslationVO;

public class SocketClient {

	private static final int port = 8999;
	private static final String addressString = "localhost";
	private static Socket socket = null;

	public static void main(String[] args) {
		//
		String[] urls = {

		"http://www.baidu.com" };
		try {

			for (String string : urls) {
				socket = new Socket(addressString, port);
				BufferedWriter writer = getWriter(socket.getOutputStream());
				TranslationVO translationVO = new TranslationVO();
				translationVO.setActionEnum(ActionEnum.DOWNLOAD);
				translationVO.setData(string);
				writer.write(JSON.toJSONString(translationVO));
				writer.flush();
				socket.close();
			}

		} catch (IOException e) {
			//
			e.printStackTrace();
		}

	}

	private static BufferedWriter getWriter(OutputStream outputStream) {
		return new BufferedWriter(new OutputStreamWriter(outputStream));
	}
}
