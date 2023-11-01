package com.dexcode.apiserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI awesomeAPI() {
        return new OpenAPI()
                .info(new Info().title("RESTful Architecture API")
                        .description("API Documentation RESTFul API")
                        .version("1.0")
                        .contact(new Contact().email("aeitiao@gmail.com").name("Adek Kristiyanto").url("www.linkedin.com/in/adek-krist"))
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("RESTFul API Architecture")
                        .url("www.linkedin.com/in/adek-krist"));
    }

}
