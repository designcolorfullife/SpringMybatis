package com.zhangwei.learning.net.info;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.util.StringUtils;

import com.zhangwei.learning.utils.LoggerUtils;

public class DownloadInfo {

	private static final int WRITE_LOCK_TIME = 1;
	private static final int READ_LOCK_TIME = 1;
	private static ConcurrentHashMap<String, String> downloadedInfo = new ConcurrentHashMap<String, String>();
	private static ConcurrentHashMap<String, String> downloadingInfo = new ConcurrentHashMap<String, String>();

	private static ReadWriteLock readWriteLocklock = new ReentrantReadWriteLock(
			true);

	private DownloadInfo() {

	}

	public static void putDownloadedInfo(String url, String file) {
		Lock lock = readWriteLocklock.writeLock();
		try {
			lock.tryLock(WRITE_LOCK_TIME, TimeUnit.SECONDS);
			if (downloadedInfo.containsKey(url)) {
				LoggerUtils.Info("已经存在url:" + url + "|File:" + file);
			}
			downloadedInfo.put(url, file);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public static String getDownloadedAbsoluteFile(String url) {
		String pathString = null;
		Lock lock = readWriteLocklock.readLock();
		try {
			lock.tryLock(READ_LOCK_TIME, TimeUnit.SECONDS);
			pathString = downloadedInfo.get(url);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return pathString;

	}

	public static void putDownloadingInfo(String url, String file) {
		Lock lock = readWriteLocklock.writeLock();
		try {
			lock.tryLock(WRITE_LOCK_TIME, TimeUnit.SECONDS);
			if (downloadingInfo.containsKey(url)) {
				LoggerUtils.Info("已经存在url:" + url + "|File:" + file);
			}
			downloadingInfo.put(url, file);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static String getDownloadingAbsoluteFile(String url) {

		String pathString = null;
		Lock lock = readWriteLocklock.readLock();
		try {
			lock.tryLock(READ_LOCK_TIME, TimeUnit.SECONDS);
			pathString = downloadingInfo.get(url);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return pathString;
	}

	public static void toFinishDownloadingInfo(String url) {
		Lock lock = readWriteLocklock.writeLock();
		try {
			lock.tryLock(2, TimeUnit.SECONDS);
			String pathString = downloadingInfo.remove(url);
			if (!StringUtils.isEmpty(pathString)) {
				downloadedInfo.put(url, pathString);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void printInfo() {
		Lock lock = readWriteLocklock.readLock();

		try {
			lock.tryLock(READ_LOCK_TIME, TimeUnit.SECONDS);
			LoggerUtils.Info("下载完成");
			for (Map.Entry<String, String> entry : downloadedInfo.entrySet()) {
				LoggerUtils.Info("[URL:" + entry.getKey() + " | PATH:"
						+ entry.getValue() + "]");
			}
			LoggerUtils.Info("正在下载");
			for (Map.Entry<String, String> entry : downloadingInfo.entrySet()) {
				LoggerUtils.Info("[URL:" + entry.getKey() + " | PATH:"
						+ entry.getValue() + "]");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}
