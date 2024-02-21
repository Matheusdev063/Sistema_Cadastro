package com.mtdev00.Sistema_Cadastro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mtdev00.Sistema_Cadastro.Resourche"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API OrderHub")
                .description("This API enables the registration and management of orders, clients, and products, offering CRUD operations alongside authentication and authorization features for secure access.")
                .version("1.0.0")
                .contact(new Contact("Matheus Vitor", "https://www.linkedin.com/in/matheus-vitor-8290b0286/", "matheusvitor.dev00@gmail.com"))
                .build();
    }
}
