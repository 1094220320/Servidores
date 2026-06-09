package com.techgear;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI techGearOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TechGear API")
                        .description("API REST para la tienda de hardware y software TechGear")
                        .version("1.0.0"));
    }
}
