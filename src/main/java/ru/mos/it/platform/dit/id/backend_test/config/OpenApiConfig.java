package ru.mos.it.platform.dit.id.backend_test.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info());
    }

    private Info info() {
        return new Info()
                .title("WHERE IS FLUFFY API")
                .version("v1");
    }

}
