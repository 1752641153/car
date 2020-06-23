package com.itwanli;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
@EnableEncryptableProperties  //开启数据库加密功能
public class CarportApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarportApplication.class, args);
    }

}
