package br.com.victor.ambev.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Ambev Pedidos API",
                version = "1.0",
                description = "Documentação da API para consulta de pedidos",
                license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
        )
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi pedidoApi() {
        return GroupedOpenApi.builder()
                .group("Pedidos")
                .pathsToMatch("/api/orders/**")
                .build();
    }
}