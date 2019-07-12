package com.longjiang.stormstout.download.Impl;

import com.longjiang.stormstout.download.Downloader;
import com.longjiang.stormstout.utils.HttpRequestUtil;
import org.springframework.stereotype.Service;

/**
 * @Author:longjiang
 * @date:下午2:02 2019/7/12
 * @Description:
 **/
@Service
public class DownloaderImpl implements Downloader {
    @Override
    public String download(String url) {
        return HttpRequestUtil.httpGet(url,6000);
    }
}
