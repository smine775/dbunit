package com.qiaomu.test.config;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: TheBigBlue
 * @Description: 服务启动后，初始化数据库
 * @Date: 2019/9/19
 */
@Component
public class ApplicationRunnerService implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRunnerService.class);

    @Autowired
    @Qualifier("h2TemplateOne")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("h2TemplateTwo")
    private JdbcTemplate jdbcTemplate2;

    @Value("${invoke.schema.location}")
    private String schema;

    /**
     * @Author: TheBigBlue
     * @Description: 项目启动，执行sql文件初始化
     * @Date: 2019/9/19
     * @Param args:
     * @Return:
     **/
    @Override
    public void run(ApplicationArguments args) {
        String schemaContent = this.getFileContent(schema);
        jdbcTemplate.execute(schemaContent);
        jdbcTemplate2.execute(schemaContent);
    }

    /**
     * @Author: TheBigBlue
     * @Description: 获取classpath下sql文件内容
     * @Date: 2019/9/19
     * @Param filePath:
     * @Return:
     **/
    private String getFileContent(String filePath) {
        BufferedReader bufferedReader = null;
        String string;
        StringBuilder data = new StringBuilder();
        try {
            ClassPathResource classPathResource = new ClassPathResource(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));
            while ((string = bufferedReader.readLine()) != null) {
                data.append(string);
            }
        } catch (IOException e) {
            LOGGER.error("加载ClassPath资源失败", e);
        }finally {
            if(null != bufferedReader){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }
}