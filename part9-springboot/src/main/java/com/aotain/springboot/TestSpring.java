package com.aotain.springboot;

import com.aotain.springboot.entity.Student;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/28
 */
public class TestSpring {

    @Test
    public void testSpring() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        Student student = (Student) ctx.getBean("student");
        System.out.println(student);
    }

}
