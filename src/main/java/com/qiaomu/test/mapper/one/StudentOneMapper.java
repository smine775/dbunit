package com.qiaomu.test.mapper.one;/**
 * ClassName:StudentMapper
 * Package:com.qiaomu.test.mapper.one
 * Decription:
 *
 * @date:2020/7/15 15:36
 * @author:shiqikun
 */

import com.qiaomu.test.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author 山东彭于晏
 * 2020/7/15
 */
@Mapper
public interface StudentOneMapper {

    int insert(Student student);
}
