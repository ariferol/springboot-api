package tr.com.sample.api.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author arif.erol
 */
@SpringBootApplication
@ComponentScan({"tr.com.sample.api.v1", "tr.com.common"})
public class SampleApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SampleApiApplication.class, args);
	}
}
