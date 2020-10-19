package co.synext;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@MapperScan("co.synext.mybatis.mapper")
@EnableSwagger2
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GovernApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovernApplication.class, args);
    }
}
