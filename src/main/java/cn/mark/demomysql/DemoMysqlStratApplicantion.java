package cn.mark.demomysql;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//项目打成war包(部署内部服务器上)
public class DemoMysqlStratApplicantion extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoMysqlApplication.class);
    }
}
