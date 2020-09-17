package com.aotain.springboot;

import com.aotain.springboot.aop.IBaseService;
import com.aotain.springboot.entity.Course;

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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-test.xml");
        Course course = (Course) ctx.getBean("course");
        System.out.println(course);
    }

    @Test
    public void testSpringAop() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-aop.xml");
        IBaseService baseService = (IBaseService) ctx.getBean("baseServiceImpl");
        baseService.hello();

    }
}
