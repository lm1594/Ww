package lkm.ww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * WW Application
 * @author lkm
 * @since 2020.04.02
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "lkm.ww")
public class WwApplication {

	public static void main(String[] args) {
		SpringApplication.run(WwApplication.class, args);
	}

}
