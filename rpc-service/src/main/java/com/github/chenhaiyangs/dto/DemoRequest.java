package com.github.chenhaiyangs.dto;

import java.io.Serializable;

/**
 * @author chenhaiyang
 */
public class DemoRequest implements Serializable{

    private String userName;

    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DemoBean{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
