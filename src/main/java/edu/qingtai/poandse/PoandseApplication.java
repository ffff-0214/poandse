package edu.qingtai.poandse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Repository;

@MapperScan(basePackages = "edu.qingtai.poandse.mapper", annotationClass = Repository.class)
@SpringBootApplication
@EnableEurekaClient
public class PoandseApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoandseApplication.class, args);
    }

}
