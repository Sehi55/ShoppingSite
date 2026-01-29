package org.sparta.shopping.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
                title = "ShoppingSite API",
                version = "v1",
                description = "Shopping 서비스 API 문서"
        )
)
@Configuration
public class SwaagerConfig {
}
