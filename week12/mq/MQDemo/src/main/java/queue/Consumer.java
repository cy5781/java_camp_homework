package queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    public static void main(String[] args) throws JMSException {
        //1.创建connectionfacoty
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //2.创建connnect,并启动connnect
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3.创建session,第一个参数是是否使用事务，第二个参数是确认机制
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地【消费者与生产者的目的地相同才能进行消息传递】
        Destination destination = session.createQueue("message");
        //5.创建消费者，第一个参数是目的地，此时创建的消费者要与目的地进行绑定。
        MessageConsumer consumer = session.createConsumer(destination);
        //6.使用消费者接受消息消息
        TextMessage message = (TextMessage) consumer.receive();
        System.out.println("接收到的消息是 "+message.getText());
        //8.提交事务
        session.commit();
        //9.关闭资源
        session.close();
        connection.close();
    }
}
