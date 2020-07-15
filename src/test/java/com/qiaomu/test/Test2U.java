package com.qiaomu.test;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader;
import com.qiaomu.test.domain.User;
import com.qiaomu.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;

/**
 * ClassName:Test2U
 * Package:com.qiaomu.test
 * Decription:
 *
 * @date:2020/7/14 17:32
 * @author:shiqikun
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(dataSetLoader = ReplacementDataSetLoader.class,databaseConnection={"masterDataSource","slaveofDataSource"})
public class Test2U {

    @Autowired
    private UserService userService;

    @Test
    @DatabaseSetup(connection = "slaveofDataSource",type = DatabaseOperation.CLEAN_INSERT, value = { "classpath:conf/insertUser.xml" })
    @ExpectedDatabase(connection = "slaveofDataSource",value = "classpath:conf/updateUser.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(connection = "slaveofDataSource",type = DatabaseOperation.DELETE_ALL, value = { "classpath:conf/user.xml" })
    public void test2(){

        try {
            String id = "6";
            String name = "mou";
            int age = 20;
            User user  = new User();
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            int i= userService.update(user);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

   /* @Test
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
        studentAndUserService.select(id);

    }*/
}
