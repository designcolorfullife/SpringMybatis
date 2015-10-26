package com.zhangwei.learning.net.downloader;

/**
 * 具体的下载处理器，主要是为了解决不同的下载协议
 * Created by design on 15-10-15.
 */
public interface SpecificDownloader {

    public void Download(String url);
}
