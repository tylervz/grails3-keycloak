package helloworld

import org.keycloak.adapters.springsecurity.KeycloakConfiguration
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.core.io.Resource
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.security.web.session.HttpSessionEventPublisher

@KeycloakConfiguration
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    // TODO: figure out what path the keycloak.configurationFile environment variable needs to be in order for keycloak.json to get located
//    static {
//        @Value("${keycloak.configurationFile:public/WEB-INF/keycloak.json}")
//        private Resource keycloakConfigFileResource
//    }

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider())
    }

    /**
     * Defines the session authentication strategy.
     *
     * You must provide a session authentication strategy bean which should be of type
     * RegisterSessionAuthenticationStrategy for public or confidential applications
     * and NullAuthenticatedSessionStrategy for bearer-only applications.
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl())
    }

    // Might need to do this.
    @Bean
    ServletListenerRegistrationBean<HttpSessionEventPublisher> getHttpSessionEventPublisher() {
        new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher())
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http)
        // Limit the customer path to secured Users
        // Limit the admin path to secured Admins
        http
                .authorizeRequests()
                .antMatchers("/customer/*").hasAnyAuthority("ROLE_OJT_USER")
                .antMatchers("/admin/*").hasAnyAuthority("ROLE_OJT_ADMIN")
                .anyRequest().permitAll()

//        Note: .antMatchers("/customer/*").hasRole("ROLE_OJT_USER") does not seem to work

        // Secure all paths except for assets
//        http
//                .logout()
//                .logoutSuccessUrl("/sso/login") // Override Keycloak's default '/'
//                .and()
//                .authorizeRequests()
//                .antMatchers("/assets/*").permitAll()
//                .anyRequest().hasAnyAuthority("ROLE_OJT_USER", "ROLE_OJT_ADMIN")
    }
}
