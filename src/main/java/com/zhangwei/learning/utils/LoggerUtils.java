package com.zhangwei.learning.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 打日志的小工具
 * 
 * @author Administrator
 * 
 */
public class LoggerUtils {

	private static Logger logger = LoggerFactory.getLogger(LoggerUtils.class);

	static {
	}

	/**
	 * 
	 * @param logger
	 * @param info
	 */
	public static void Info(Logger logger, String info) {
		if (logger.isInfoEnabled()) {
			logger.info(info);
		}
	}

	/**
	 * 提供一个默认的logger
	 * 
	 * @param info
	 */
	public static void Info(String info) {
		Info(logger, info);
	}

	public static void Info(String[] info) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String s : info) {
			stringBuilder.append(s);
		}
		Info(logger, stringBuilder.toString());
	}

	/**
	 * 可变参数列表实现多个参数打印
	 * 
	 * @param args
	 */
	public static void Info(Object... args) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Object aObject : args) {
			stringBuilder.append(aObject);
		}
		Info(logger, stringBuilder.toString());
	}
}
