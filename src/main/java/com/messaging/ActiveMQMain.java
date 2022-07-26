package com.messaging;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQMain 
{
	public static void main(String[] args) throws JMSException
	{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		
		Thread topicConsumerThread = new Thread(new TopicConsumer(connectionFactory));
		topicConsumerThread.start();
		try {
			Thread.sleep(7000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		Thread topicProducerThread = new Thread(new TopicProducer(connectionFactory));
		topicProducerThread.start();
	}

	
}
