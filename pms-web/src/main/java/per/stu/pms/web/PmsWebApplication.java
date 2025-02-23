package per.stu.pms.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"per.stu.pms.*"}) // 多模块项目中，必需手动指定扫描 per.stu.pms 包下面的所有类
@MapperScan("per.stu.pms.common.domain.mapper") // 扫描 MyBatis Mapper 接口
public class PmsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmsWebApplication.class, args);
    }

}
