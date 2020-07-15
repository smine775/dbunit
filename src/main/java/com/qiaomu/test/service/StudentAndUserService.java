package com.qiaomu.test.service;

import com.qiaomu.test.domain.Student;
import com.qiaomu.test.domain.User;
import com.qiaomu.test.mapper.one.StudentOneMapper;
import com.qiaomu.test.mapper.one.UserOneMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName:StudentAndUserService
 * Package:com.qiaomu.test.service
 * Decription:
 *
 * @date:2020/7/15 15:38
 * @author:shiqikun
 */
@Service
public class StudentAndUserService {

    @Resource
    private StudentOneMapper studentOneMapper;
    @Resource
    private UserOneMapper userOneMapper;

    public void insert() {
        User user = new User();
        Student student = new Student();

        user.setId("1");
        user.setName("qiaomu");
        user.setAge(23);

        student.setId("2");
        student.setClassName("yiban");
        student.setAge(18);
        userOneMapper.insert(user);
        studentOneMapper.insert(student);

    }

    public void insert2() {
        User user = new User();
        Student student = new Student();

        user.setId("2");
        user.setName("qiaomummd");
        user.setAge(24);

        student.setId("3");
        student.setClassName("erban");
        student.setAge(19);
        userOneMapper.insert(user);
        studentOneMapper.insert(student);
    }
}
