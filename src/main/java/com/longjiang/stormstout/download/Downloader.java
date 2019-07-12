package com.longjiang.stormstout.download;

/**
 * @Author:longjiang
 * @date:下午2:29 2019/7/10
 * @Description: 下载器
 * 负责下载网页，并把下载的内容传给pipeline进行后续保存处理
 **/
public interface Downloader {

    String download(String url);

}
