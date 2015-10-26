package com.zhangwei.learning.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by design on 15-10-15.
 */
public class ConfigMap {

	private Map<String, String> configs = new HashMap<String, String>();

	// public void setConfigs(Map<String, String> configs) {
	// this.configs = configs;
	// }
	public String getConfig(String key) {
		return configs.get(key);
	}

	public void setConfigs(Map<String, String> configs) {
		this.configs = configs;
	}

	public void setProperties(String file) {
		InputStream inputStream = null;
		Properties properties = new Properties();
		try {
			inputStream = new ClassPathResource(file).getInputStream();

			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			LoggerUtils.Info("初始化配置Key:" + entry.getKey() + "|value:"
					+ entry.getValue());
			configs.put(entry.getKey().toString(), entry.getValue().toString());
		}
		if (configs.isEmpty()) {
			LoggerUtils.Info("初始化配置为空");
		}
	}
}
