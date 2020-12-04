package micro.show.zuul.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Disable CSRF (cross site request forgery)
        httpSecurity.cors().and().csrf().disable();

        // No session will be created or used by spring security
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // if a user try to access a resource without having enough permissions
        httpSecurity.exceptionHandling().accessDeniedPage("/login");

        // Entry points
        httpSecurity.authorizeRequests()
                .antMatchers("/api/**").authenticated();

    }

    @Override
    public void configure(WebSecurity web) {
        // Allow eureka client to be accessed without authentication
        web.ignoring().antMatchers("/api/auth/**");
    }

}
