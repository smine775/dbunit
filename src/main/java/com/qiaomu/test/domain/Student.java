package com.qiaomu.test.domain;

/**
 * ClassName:Student
 * Package:com.qiaomu.test.domain
 * Decription:
 *
 * @date:2020/7/15 15:34
 * @author:shiqikun
 */
public class Student {


    private  String id;
    private  String  className;
    private  int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
