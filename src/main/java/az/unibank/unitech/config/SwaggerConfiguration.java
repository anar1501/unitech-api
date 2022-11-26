package az.unibank.unitech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
@CrossOrigin
public class SwaggerConfiguration {
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Spring Boot UNITECH REST APIs",
                "Spring Boot UNITECH REST API Documentation",
                "1",
                "Terms of service",
                new Contact("Anar Mammmadov", "", "mammadov_anar@outlook.com"),
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("az.unibank.unitech"))
                .paths(PathSelectors.any())
                .build();
    }

}

