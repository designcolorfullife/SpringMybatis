package com.zhangwei.learning.net.downloader;

import com.zhangwei.learning.utils.LoggerUtils;

/**
 * Created by design on 15-10-15.
 */
public class HttpDownloader implements SpecificDownloader {

    public void Download(String url) {
        LoggerUtils.Info("download:" + url);
        // URL url = new URL(translationVO.getData().toString());
        // HttpURLConnection httpURLConnection = (HttpURLConnection) url
        // .openConnection();
        // InputStream inputStream = new BufferedInputStream(
        // httpURLConnection.getInputStream());

        // DataInputStream dataInputStream = new
        // DataInputStream(inputStream);
        //			DownloadManager downloadManager = DownloadManager.getInstance();
//			DownloadMission downloadMission = new DownloadMission(translationVO
//					.getData().toString(), configMap.getConfig(ConfigsEnum.DOWNLOAD_SAVE_DIR), temp[temp.length - 1]);
//			downloadManager.addMission(downloadMission);
//			DownloadInfo.putDownloadingInfo(
//					translationVO.getData().toString(),
//					downloadMission.getSaveDirectory() + "//"
//							+ downloadMission.getSaveName());
//			downloadManager.start();
//			while (!downloadMission.isFinished()) {
//				Thread.sleep(1000);
//				LoggerUtils.Info(downloadMission.getProgressFileName()
//						+ "下载中,文件大小:"
//						+ downloadMission.getFileSize()
//						+ "速度为:"
//						+ downloadMission.getSpeed()
//						/ 1000
//						+ "kb/s已经下载"
//						+ downloadMission.getDownloadedSize()
//						+ "已完成:"
//						+ (downloadMission.getFileSize() == 0 ? null : Long
//								.valueOf(downloadMission.getDownloadedSize())
//								* 100
//								/ Long.valueOf(downloadMission.getFileSize()))
//						+ "%");
//				DownloadInfo.printInfo();
//			}
//			LoggerUtils.Info(translationVO.toString() + "执行完闭");
//			DownloadInfo.toFinishDownloadingInfo(translationVO.getData()
//					.toString());
        // OutputStream out = new BufferedOutputStream(new FileOutputStream(
        // file));
        // byte[] buf = new byte[1024];
        // int size = 0;
        // while ((size = inputStream.read(buf)) != -1) {
        // out.write(buf, 0, size);
        // }
//			DownloadInfo.putDownloadedInfo(translationVO.getData().toString(),
//					file.getAbsolutePath());
//			DownloadInfo.printInfo();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
    }
}
