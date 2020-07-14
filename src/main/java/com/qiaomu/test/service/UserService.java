package com.qiaomu.test.service;

import com.qiaomu.test.domain.User;
import com.qiaomu.test.mapper.one.UserOneMapper;
import com.qiaomu.test.mapper.two.UserTwoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName:UserService
 * Package:com.qiaomu.test.service
 * Decription:
 *
 * @date:2020/7/13 20:13
 * @author:qiaomu
 */
@Service
public class UserService {

    @Resource
    private UserOneMapper userOneMapper;

    public int insert(User user) {
        return userOneMapper.insert(user);
    }

    @Resource
    private UserTwoMapper userTwoMapper;

    public int update(User user) {
        return userTwoMapper.update(user);
    }
}
