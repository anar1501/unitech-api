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
//    public static final String AUTHORIZATION_HEADER = "Authorization";

//    private ApiKey apiKey() {
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }

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
//                .securityContexts(Collections.singletonList(securityContext()))
//                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("az.unibank.unitech"))
                .paths(PathSelectors.any())
                .build();
    }

//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }

//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
//    }
}

