package com.example.demo.rocketmq;

import java.util.concurrent.TimeUnit;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class RocketMqProducter {

	public static void main(String[] args) throws MQClientException, InterruptedException {
		DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
		producer.setNamesrvAddr("127.0.0.1:9876");
		producer.setInstanceName("Producer");
		producer.setSendMsgTimeout(3000);
		producer.setRetryTimesWhenSendFailed(2);
		producer.start();
		for (int i = 0; i < 200; i++) {
			try {
				{
					Message msg = new Message("TopicTest1", // topic
							"TagA", // tag
							"OrderID001", // key
							("Hello MetaQ" + i).getBytes());// body
					SendResult sendResult = producer.send(msg);
					System.out.println(sendResult);
				}
				{
					Message msg = new Message("TopicTest2", // topic
							"TagB", // tag
							"OrderID0034", // key
							("Hello MetaQ" + i).getBytes());// body
					SendResult sendResult = producer.send(msg);
					System.out.println(sendResult);
				}
				{
					Message msg = new Message("TopicTest3", // topic
							"TagC", // tag
							"OrderID061", // key
							("Hello MetaQ" + i).getBytes());// body
					SendResult sendResult = producer.send(msg);
					System.out.println(sendResult);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			TimeUnit.MILLISECONDS.sleep(1000);
		}
		/**
		 * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从服务器上注销自己
		 * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
		 */
		producer.shutdown();

	}

}
