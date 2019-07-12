# stormsout


![流程图](https://github.com/longxiaoyun/stormsout/blob/master/intro.png)


>调度器：广度优先遍历或者深度优先遍历，及确定接下来带下载url的优先级，主要作用是决定下一个要下载的url，预留接口



>下载器：下载网页，要求多线程高并发，至少需要一个种子url，预留接口可以设置headers,proxy



>解析器：对下载的网页解析，这个项目里主要是提取出页面中的所有url，以保证子子孙孙无穷尽也的采集下去



>存储网页：对结果网页的保存，预留接口，以保证可以自定义实现存储方式



>url去重：采用布隆过滤器(Redis)实现，对采集过的url进行过滤



>Spider：实现以上接口，自定义爬取优先级,header,proxy,存储方式，去重等等，作为整个爬虫的入口
