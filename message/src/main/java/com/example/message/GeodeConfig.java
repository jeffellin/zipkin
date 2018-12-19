package com.example.message;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.apache.geode.cache.GemFireCache;

@Configuration
public class GeodeConfig {



	@Bean
	public ClientRegionFactoryBean<String,String> propRegion(GemFireCache gemfireCache){


		ClientRegionFactoryBean<String,String> clientRegion = new ClientRegionFactoryBean<>();
		clientRegion.setCache(gemfireCache);
		clientRegion.setShortcut(ClientRegionShortcut.LOCAL);
		return clientRegion;

	}

}

