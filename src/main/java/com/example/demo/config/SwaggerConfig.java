package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.file.Path;

// @Configuration anotasyonu, o sınıfın bir yapılandırma sınıfı olduğunu belirler.
@Configuration

// @EnableSwagger2 anotasyonu, ayarların swagger için kullanılacağını belirtir ve aktive eder.
@EnableSwagger2
public class SwaggerConfig {

    // API'lar için Swagger'ın kendi içerisindeki ayarlar yapılır. Bu ayarlar Spring framework'ü için bir arayüz oluşturmayı sağlar.
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("customer-api")
                    .apiInfo(apiInfo())
                    .select()
                    .paths(PathSelectors.ant("/api/v1/**"))
                    .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Customer API")
                .description("Customer API with CRUD Operations")
                .license("MIT Licence")
                .licenseUrl("example@gmail.com")
                .version("1.0")
                .build();
    }
}