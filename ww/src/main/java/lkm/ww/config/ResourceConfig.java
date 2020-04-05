package lkm.ww.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * Resource 설정
 * @author lkm
 * @since 2020.04.02
 */
@Component
@PropertySource(value = {
		"classpath:tomcat.properties"
		, "classpath:datasource.properties"
		, "classpath:properties/config.properties"
})
public class ResourceConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertSourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
		return propertySourcesPlaceholderConfigurer;
	}
}
