package com.aotain.springboot.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/31
 */
@Data
public class Course {

    private Student student;

    @Override
    public String toString() {
        return "Course{" +
                "student=" + student +
                '}';
    }
}
