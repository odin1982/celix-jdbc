package org.celix.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:messages.properties")
@ConfigurationProperties("m")
public class MessagesPropertiesConfig {
	@Autowired
	Environment env;
	
	public String getProperty(String key) {
		return env.getProperty(key);
	}

}
