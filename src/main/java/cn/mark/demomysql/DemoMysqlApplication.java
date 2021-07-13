package cn.mark.demomysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableCaching
//@MapperScan("com.mark.demomysql.mapper")
@EnableTransactionManagement
public class DemoMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMysqlApplication.class, args);
	}

}
