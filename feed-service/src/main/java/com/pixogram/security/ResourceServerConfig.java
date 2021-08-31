package com.pixogram.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import lombok.extern.slf4j.Slf4j;


@SuppressWarnings("deprecation")
@Configuration
@EnableResourceServer
@Slf4j
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${security.authorization.check-token-access}")
	private String securityUrlCheckToken;

	@Value("${security.client.client-id}")
	private String securityClientId;

	@Value("${security.client.client-secret}")
	private String securityClientSecret;

	@Value("${security.resource.id}")
	private String securityResourceId;

	@Value("${serviceId.user}")
	private String authServiceId;

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Override
	public void configure(final ResourceServerSecurityConfigurer config) {
		config.tokenServices(tokenServices());
		config.resourceId(securityResourceId);
	}

	@Primary
	@Bean
	public RemoteTokenServices tokenServices() {
		RemoteTokenServices tokenService = new RemoteTokenServices();
		
		String url =getDynamicUrlFromEureka(securityUrlCheckToken, authServiceId);
		if(!url.contains("http")) {
			url = "http://localhost:8090"+securityUrlCheckToken;
		}
		tokenService.setCheckTokenEndpointUrl(url);
		tokenService.setClientId(securityClientId);
		tokenService.setClientSecret(securityClientSecret);
		return tokenService;
	}
	
	public String getDynamicUrlFromEureka(String url, String serviceId) {
		if (url != null) {
			List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
			if (!instances.isEmpty()) {
				ServiceInstance serviceInstance = instances.get(0);
				url = serviceInstance.getUri().toString() + "/" + url;
			}
		}
		log.info("Service Id : " + serviceId + " URL : " + url);
		return url;
	}
}