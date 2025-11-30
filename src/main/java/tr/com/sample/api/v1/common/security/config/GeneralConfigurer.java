package tr.com.sample.api.v1.common.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * @author arif.erol
 */
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class GeneralConfigurer {

    /*Swagger url icin http://localhost:90/sample-api/swagger-ui/index.html*/
    @Bean
    public OpenAPI customOpenAPI(@Value("${app.description}") String description, @Value("${app.version}") String version) {
        return new OpenAPI()
                .info(new Info().title("Sample Api Uygulaması")
                        .version(version)
                        .description(description)
                        .license(new License().name("API License")));
    }
}
