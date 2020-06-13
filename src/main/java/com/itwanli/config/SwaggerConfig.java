package com.itwanli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2         //开启Swagger2
public class SwaggerConfig {

    //配置了Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev");
        //获取项目的环境（在开发环境中使用,生产环境中不使用）
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("小欠")
                .enable(flag)   //enable是否启动Swagger,如果为false,则Swagger不能再浏览器中访问
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.itwanli"))
                .build();
    }

    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo(){

        //作者信息
        Contact contact = new Contact("小钦","http://3090z0549n.imdo.co","1752641153@qq.com");

        return new ApiInfo(
                "小钦的SwaggerAPI文档",
                "小钦有点酷",
                "1.0",
                "http://3090z0549n.imdo.co",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }

}
