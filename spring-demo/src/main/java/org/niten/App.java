package org.niten;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));

        ApplicationContext factory = new ClassPathXmlApplicationContext("src/main/resources/spring.xml");

        Alien obj = (Alien) factory.getBean("alien");
        obj.code();
    }
}
