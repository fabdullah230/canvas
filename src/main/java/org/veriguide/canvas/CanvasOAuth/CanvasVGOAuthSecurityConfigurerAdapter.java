package org.veriguide.canvas.CanvasOAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;


@Configuration
@EnableWebMvc
@EnableWebSecurity
public class CanvasVGOAuthSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private CanvasVG0LeggedOAuthProviderProcessingFilter zeroLeggedOAuthProviderProcessingFilter;
    private OAuthProviderTokenServices oauthProviderTokenServices;
    @Autowired
    CanvasVGConsumerDetailsService oauthConsumerDetailsService;
    @Autowired
    CanvasVGOAuthNonceServices oauthNonceServices;
    @Autowired
    CanvasVGOAuthAuthenticationHandler oauthAuthenticationHandler;
    @Autowired
    CanvasVGOAuthProcessingFilterEntryPoint oauthProcessingFilterEntryPoint;

    @PostConstruct
    public void init() {
        oauthProviderTokenServices = new InMemoryProviderTokenServices();
        zeroLeggedOAuthProviderProcessingFilter = new CanvasVG0LeggedOAuthProviderProcessingFilter(oauthConsumerDetailsService, oauthNonceServices, oauthProcessingFilterEntryPoint, oauthAuthenticationHandler, oauthProviderTokenServices, false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .addFilterBefore(zeroLeggedOAuthProviderProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/cookie", "/resttest")
                .permitAll()
                .anyRequest().hasRole("OAUTH")
                .and().httpBasic().disable()//all disabled for testing purposes
                .csrf().disable()
                .headers().frameOptions().disable();//allowing iframe

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }



}




