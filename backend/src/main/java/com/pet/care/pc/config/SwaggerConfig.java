package com.pet.care.pc.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@io.swagger.v3.oas.annotations.security.SecurityScheme(
  type = SecuritySchemeType.APIKEY,
  name = "Access-Token",
  in = SecuritySchemeIn.HEADER
)
@OpenAPIDefinition(security = { @SecurityRequirement(name = "Access-Token") })
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .info(
        new Info()
          .title("펫 케어 프로젝트 API")
          .description("펫 케어 관련 기능들을 제공합니다.")
          .version("1.0.0")
      )
      .components(
        new Components()
          .addSecuritySchemes(
            "bearer-key",
            new SecurityScheme()
              .type(Type.HTTP)
              .scheme("bearer")
              .bearerFormat("JWT")
          )
      );
  }
}
