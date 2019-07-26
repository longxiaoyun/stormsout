# stormsout


![流程图](https://github.com/longxiaoyun/stormsout/blob/master/intro.png)

>seed_url：种子url


>make_request_from_url：把url组装成request,然后放到RequestQueue里，给Download来下载


>RequestQueue：request队列


>Download：下载网页，下载后返回的response放到ResponseQueue


>RequestQueue:response队列


>PageAnalyzer：对下载的网页解析，这个项目里主要是提取出页面中的所有url，以保证子子孙孙无穷尽也的采集下去



>save_pipeline：对结果网页的保存



###### 启动

Run.class

设置好种子url即可
