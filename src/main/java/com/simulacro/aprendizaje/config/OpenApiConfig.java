package com.simulacro.aprendizaje.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;




@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = " Learning platform api - SPRINGBOOT",
        version = "1.0",
        description = "online learning platform to facilitate access to courses and educational materials.",
       
        license = @License(
            name = "JSFM"
        ),
        contact = @Contact(
                        name = "Juan Sebastián Fernández Montoya",
                        url = "https://juansefdz.com/"
                        
        )
       
        
    ) 
)

public class OpenApiConfig {
    
}
