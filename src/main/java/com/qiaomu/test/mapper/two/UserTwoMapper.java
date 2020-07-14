package com.qiaomu.test.mapper.two;/**
 * ClassName:UserMapper
 * Package:com.qiaomu.test.mapper.two
 * Decription:
 *
 * @date:2020/7/13 20:14
 * @author:qiaomu
 */

import com.qiaomu.test.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author qiaomu
 * 2020/7/13
 */

@Mapper
public interface UserTwoMapper {
    int update(User user);
}
