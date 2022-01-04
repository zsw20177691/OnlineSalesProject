package com.Important.config;

import com.Important.WebApplication;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** 标识为配置类 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable:true }")
public class SwaagerConfig implements WebMvcConfigurer {
    @Bean
    public Docket api(){
        Docket build = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("重要项目测试").description("重要项目测试").version("1.0.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Important.controller"))
                .paths(PathSelectors.any())
                .build();
        return build;
    }
}
