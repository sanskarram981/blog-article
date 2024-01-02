package com.blogarticle.app.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI getOpenApiMetaData()
    {
        return new OpenAPI()
                .info(getInfoMetaData())
                .addServersItem(new Server().url("http://localhost:1010"))
                .addServersItem(new Server().url("http://localhost:8080"))
                .components(new Components()
                                     .addSecuritySchemes("Authorization-Token",
                                     new SecurityScheme()
                                             .type(SecurityScheme.Type.HTTP)
                                             .scheme("bearer")
                                             .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("Authorization-Token"));
    }

    private Info getInfoMetaData()
    {
        return new Info()
                .title("Sihai-Backend-Apis-Documentation")
                .description("Documentation of sihai-backend-apis-using-springboot")
                .version("3.1.6")
                .license(new License().url("https://github.com/sanskarram981").name("github"))
                .contact(new Contact().url("https://github.com/sanskarram981").email("sanskarram992@gmail.com").name("sram"))
                .termsOfService("terms & conditions applicable");
    }
}
