package tr.com.sample.api.v1.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SwaggerUrlLogger implements ApplicationListener<ApplicationReadyEvent> {

    private final Environment environment;

    @Value("${server.port:90}")
    private int serverPort;

    public SwaggerUrlLogger(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String[] activeProfiles = environment.getActiveProfiles();
        for (String profile : activeProfiles) {
            if ("dev".equalsIgnoreCase(profile)) {
                String swaggerUrl = String.format("http://localhost:90/sample-api/swagger-ui/index.html", serverPort);
                System.out.println("\n----------------------------------------------------------");
                System.out.println("Swagger UI: " + swaggerUrl);
                System.out.println("----------------------------------------------------------\n");
                break;
            }
        }
    }
}