package alkemy.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import alkemy.challenge.security.JWTAuthorizationFilter;

@SpringBootApplication
public class ChallengePreAceleracionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengePreAceleracionApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests().antMatchers(HttpMethod.POST, "/auth/**").permitAll().anyRequest()
					.authenticated();
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/h2-console/**");
		}
	}

}
