package com.longjiang.stormstout.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @Author:longjiang
 * @date:下午2:35 2019/7/11
 * @Description:
 **/
@EnableRabbit
@Configuration
public class RabbitMQConfig {
    // 消息交换机的名字
    private static final String EXCHANGE = "stormsout-mq-exchange";
    // url 队列名称
    private static final String urlqueue = "url_queue";

    private static final String urlBindKey="for_url_queue";




    //RabbitMQ的配置信息
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;


    /**
     * 配置消息队列  queue
     * @return
     * true表示持久化该队列
     */
    @Bean
    public Queue UrlQueue() {
        return new Queue(urlqueue,true);
    }


    /**
     * 配置交换机
     * @return
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    /**
     * 将消息队列与交换机绑定
     * 针对消费者配置
     * @return
     */

    @Bean
    public Binding bindingUrlQueue() {
        return BindingBuilder.bind(UrlQueue()).to(defaultExchange()).with(RabbitMQConfig.urlBindKey);
    }







    //建立一个连接容器，类型数据库的连接池
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);// 确认机制
        //发布确认，template要求CachingConnectionFactory的publisherConfirms属性设置为true
        return connectionFactory;
    }


    // RabbitMQ的使用入口
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(this.connectionFactory());
        template.setMessageConverter(this.jsonMessageConverter());
        template.setMandatory(true);
        return template;
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }




    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }









}
