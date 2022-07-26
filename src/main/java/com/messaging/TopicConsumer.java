package com.messaging;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumer implements Runnable
{
	ActiveMQConnectionFactory connectionFactory = null;

	public TopicConsumer(ActiveMQConnectionFactory connectionFactory)
	{
		this.connectionFactory = connectionFactory;
	}
	public void run() {
		try
		{
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			
			Destination topicDestination = session.createTopic("publish-subscribe-topic");
			
			MessageConsumer messageConsumer = session.createConsumer(topicDestination);
			
			Message message = messageConsumer.receive();
			TextMessage textMessage = (TextMessage) message;
			
			System.out.println(textMessage.getText());
			
			session.close();
			connection.close();
		}
		catch(JMSException jmsException)
		{
			System.out.println("Exception: " + jmsException.getMessage());
		}
		
	}

}
