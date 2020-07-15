package com.qiaomu.test;

import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader;
import com.qiaomu.test.domain.Student;
import com.qiaomu.test.domain.User;
import com.qiaomu.test.service.StudentAndUserService;
import com.qiaomu.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;

/**
 * ClassName:TestU
 * Package:com.qiaomu.test
 * Decription:
 *
 * @date:2020/7/13 20:13
 * @author:qiaomu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DbUnitConfiguration(dataSetLoader = ReplacementDataSetLoader.class,databaseConnection={"masterDataSource","slaveofDataSource"})
public class TestU {
        @Resource
    //private TestDao testDao;
    private UserService userService;


    @Resource
    private StudentAndUserService studentAndUserService;

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = { "classpath:conf/user.xml" })
    @ExpectedDatabase(value = "classpath:conf/insertUser.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:conf/user.xml" })
    public void test(){
        System.out.println();
        try {
            String id = "6";
            String name = "mu";
            int age = 23;
            User user  = new User();
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            int i = userService.insert(user);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = { "classpath:conf/insertUser.xml" },connection = "slaveofDataSource")
    @ExpectedDatabase(override= true,value = "classpath:conf/updateUser.xml", assertionMode = DatabaseAssertionMode.NON_STRICT,connection = "slaveofDataSource")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:conf/user.xml" },connection = "slaveofDataSource")
    public void test2(){

        try {
            String id = "6";
            String name = "mou";
            int age = 20;
            User user  = new User();
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            int i = userService.update(user);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }







    @Test
    @DatabaseSetups({
            @DatabaseSetup(connection = "masterDataSource" ,type = DatabaseOperation.CLEAN_INSERT, value = { "classpath:conf/user.xml" }),
            @DatabaseSetup(connection = "slaveofDataSource",type = DatabaseOperation.CLEAN_INSERT, value = { "classpath:conf/Student.xml" })
    })
    @ExpectedDatabases({
            @ExpectedDatabase(connection = "masterDataSource" ,value = "classpath:conf/allInsert.xml", assertionMode = DatabaseAssertionMode.NON_STRICT),
            @ExpectedDatabase(connection = "slaveofDataSource",value = "classpath:conf/allInsertDatasourceTwo.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)

    })

    @DatabaseTearDowns({
            @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:conf/user.xml" }),
            @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:conf/Student.xml" })
    })
    public void test3(){

        studentAndUserService.insert();
        studentAndUserService.insert2();

    }
}
