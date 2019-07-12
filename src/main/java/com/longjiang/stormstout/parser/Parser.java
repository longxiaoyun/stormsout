package com.longjiang.stormstout.parser;

import com.longjiang.stormstout.scheduler.Sender;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.nodes.Document;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
