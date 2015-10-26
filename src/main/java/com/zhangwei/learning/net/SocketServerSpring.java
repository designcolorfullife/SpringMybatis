package com.zhangwei.learning.net;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhangwei.learning.utils.LoggerUtils;

/**
 * Created by design on 15-10-15.
 */
public class SocketServerSpring {
	public static void main(String[] args) {

		// BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource(
		// "spring.xml"));
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext(
				"spring1.xml");
		LoggerUtils.Info("test");
		SocketServer socketServer = (SocketServer) beanFactory
				.getBean("socketServer");
		socketServer.runserver();
	}
}
