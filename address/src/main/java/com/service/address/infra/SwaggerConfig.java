package com.service.address.infra;


import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.service.address"))
        .paths(regex("/address.*"))
        .build()
        .apiInfo(metaInfo());
  }

  private ApiInfo metaInfo() {

    return new ApiInfo(
        "Address API REST",
        "REST API to register Address.",
        "1.0",
        "Terms of Service",
        new Contact("Julia Vadillo","https://www.linkedin.com/in/julia-vadillo-632472104/",
            "juliavadillo@hotmail.com"),
        "Apache License Version 2.0",
        "https://www.apache.org/licesen.html", new ArrayList<>()
    );
  }
}
