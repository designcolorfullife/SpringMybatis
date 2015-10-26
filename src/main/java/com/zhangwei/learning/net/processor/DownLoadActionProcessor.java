package com.zhangwei.learning.net.processor;

import com.zhangwei.learning.net.downloader.SpecificDownloader;
import com.zhangwei.learning.net.vo.TranslationVO;
import com.zhangwei.learning.utils.ConfigMap;

import java.io.File;
import java.net.Socket;
import java.util.Map;

public class DownLoadActionProcessor implements ActionProcessor {

    private ConfigMap configMap;

    private Map<String, SpecificDownloader> downloaderMap;

    public void invoke(TranslationVO translationVO, Socket client) {


        String[] temp = translationVO.getData().toString().split("/");
        File file = new File("//home//design" + temp[temp.length - 1]);
        for (Map.Entry<String, SpecificDownloader> downloaderEntry : downloaderMap.entrySet()) {
            if (translationVO.getData().toString().startsWith(downloaderEntry.getKey())) {
                downloaderEntry.getValue().Download(translationVO.getData().toString());
            }
        }

    }

    public void setConfigMap(ConfigMap configMap) {
        this.configMap = configMap;
    }

    public void setDownloaderMap(Map<String, SpecificDownloader> downloaderMap) {
        this.downloaderMap = downloaderMap;
    }
}