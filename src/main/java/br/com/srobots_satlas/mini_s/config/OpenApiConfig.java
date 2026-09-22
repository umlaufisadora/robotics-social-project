package br.com.srobots_satlas.mini_s.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações globais de documentação OpenAPI (Swagger)
 */

@Configuration
public class OpenApiConfig
{
    @Bean
    public OpenAPI customOpenApi()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("Mini S - Projeto social")
                        .version("1.0")
                        .description("API RESTFul desenvolvida para projeto social das equipes de robótica")
                        .contact(new Contact()
                                .name("Isadora Umlauf")
                                .email("isadora_umlauf@estudante.sesisenai.org.br")));
    }
}
