package com.aotain.springboot.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/27
 */
@Data
public class Student {

    private String name;
    private Integer id;

    private Course course;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
