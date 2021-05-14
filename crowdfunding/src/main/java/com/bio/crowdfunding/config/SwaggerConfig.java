package com.bio.crowdfunding.config;

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

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
                .apis(RequestHandlerSelectors.basePackage("com.bio.crowdfunding.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        		.title("Bio API Rest")
        		.description("API de uma rede social de financiamento coletivo (crowdfunding) voltado para causas ambientais")
        		.version("1.0")
        		.license("MIT License")
                .licenseUrl("https://github.com/caio199307/Projeto_IntegradorG4/blob/99505619b567ba9cb970a3a5cf8fd8974d657599/LICENSE")
                .contact(contact())
                .build();
    }

    private Contact contact() {
        return new Contact("Bio Grupo 4", 
        		"https://github.com/caio199307/Projeto_IntegradorG4/tree/master",
        		"Desenvolvimento Turma 19 Generation");
    }

}
