package queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    public static void main(String[] args) throws JMSException {
        //1.创建connectionfacoty，参数是activemq的服务地址，前缀tcp代表是tcp连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //2.使用ConnectionFactory创建connnect,并启动connnect
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3.使用Connection创建session,第一个参数是是否使用事务，第二个参数是确认机制
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地(这里以PTP为例，所以目的地是一个Queue)，参数是Queue的名字
        Destination destination = session.createQueue("message");
        //5.创建生产者，第一个参数是目的地，此时创建的生产者要与目的地进行绑定。
        MessageProducer producer = session.createProducer(destination);
        //6.使用session创建消息，这里使用TEXT类型的消息
        TextMessage textMessage = session.createTextMessage("测试消息123!");
        //7.生产者发送消息
        ((javax.jms.MessageProducer) producer).send(textMessage);
        //8.提交事务
        session.commit();
        //9.关闭资源
        session.close();
        connection.close();
    }
}
