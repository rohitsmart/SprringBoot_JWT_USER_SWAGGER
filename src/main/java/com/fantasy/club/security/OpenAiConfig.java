package com.fantasy.club.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Rohit Kumar",
                        email = "rohit.cs95@outlook.com"
                ),
                description = "Club REST API spring Boot 3.3.0",
                title = "CLUB",
                version = "1.0",
                license = @License(
                        name = "Rohit Club",
                        url = "Not Yet"
                ),
                termsOfService = "nothing to show"
        ),
        servers = {
                @Server(
                        description = "localhost  environment",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = " development environment",
                        url = "http://192.168.1.14:8080"
                ),
                @Server(
                        description = " production environment",
                        url = "no url till now"
                )

        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth Bearer Token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenAiConfig {
}


