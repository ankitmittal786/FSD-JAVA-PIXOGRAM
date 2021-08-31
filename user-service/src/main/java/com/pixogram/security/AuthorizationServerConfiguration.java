	package com.pixogram.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.pixogram.service.UserService;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter 
{
	
	@Value("${security.jwt.access-token-validity-seconds}")
	private int accessTokenValiditySeconds;

	@Value("${security.jwt.refresh-token-validity-seconds}")
	private int refreshTokenValiditySeconds;
	
	/*
	@Autowired
	private  PasswordEncoder encoder;	

	
	*/
	
	
	private UserDetailService userService;
	
	@Autowired
	public AuthorizationServerConfiguration() {
		userService=new UserDetailService();
	}
	
	

	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
       
		oauthServer
			.tokenKeyAccess("permitAll()")
        	 .checkTokenAccess("isAuthenticated()")              
            ;
        
    }
    
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("pixogram")
				.authorizedGrantTypes("password","authorization_code","client_credentials")
				.secret(encoder().encode("pixogram-2021"))
				.scopes("user_info")
				.redirectUris("https://localhost:8081/health")
				.accessTokenValiditySeconds(accessTokenValiditySeconds)
				.refreshTokenValiditySeconds(refreshTokenValiditySeconds)
				.autoApprove(true)
							;
	}
	
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter()));
		endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain)
		.authenticationManager(authenticationManager).userDetailsService(userService)
		.reuseRefreshTokens(false);
	}
	
	
	//TODO-6  Uncomment the below to enable JWT tokens
	
	
	
	 @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(accessTokenConverter());
	    }

	    @Bean
	    public JwtAccessTokenConverter accessTokenConverter() {
	        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        //converter.setSigningKey("123");
	        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
	        		new ClassPathResource("pixogram.jks"), "pixogram".toCharArray());
	         converter.setKeyPair(keyStoreKeyFactory.getKeyPair("pixogram"));
	        return converter;
	    }
	    
	    
	    
	    
	
	@Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
	
}
