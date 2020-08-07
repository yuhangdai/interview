package com.aotain.java8.lambda;

import lombok.Data;

import java.util.Optional;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/07
 */
public class OptionalTest {

    //

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("test xx");
        String userName = "";
        if (user != null){
            if (user.getUserName()!=null){
                userName = user.getUserName();
            } else {
                userName = "unKnown";
            }
        }
        System.out.println(userName);
        // 使用optional改写
        String name = Optional.ofNullable(user).map(user1 -> user1.getUserName()).orElse("unKnown");
        System.out.println(name+"=====");
    }

    @Data
    static class User{
        private String userName;
    }
}
