package com.app.my_market.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TEST-MARKET API")
                        .version("1.0")
                        .description("Una API de productos y compras desarrollada para atender peticiones GET, POST y DELETE ")
                        .license(new License().name("Ache002").url("http://springdoc.org")));
    }
}
