package com.longjiang.stormstout.parser;

import java.util.List;

/**
 * @Author:longjiang
 * @date:下午4:15 2019/7/10
 * @Description: 解析器，负责解析页面里的url，以及其他特定内容
 * 解析出的url添加到待爬取url队列
 * 解析出的网页内容传给pipeline进行存储等后续操作
 **/

public interface Parser {

    List<String> fetchUrl(String content);


}
