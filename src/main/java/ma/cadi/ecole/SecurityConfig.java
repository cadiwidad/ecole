package ma.cadi.ecole;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * //les donnees ici sont stockee en memoire
		 * auth.inMemoryAuthentication().withUser("widad").password("{noop}123").roles(
		 * "ADMIN","SCOLARITE");
		 * auth.inMemoryAuthentication().withUser("rania").password("{noop}123").roles(
		 * "SCOLARITE");
		 * auth.inMemoryAuthentication().withUser("ayoub").password("{noop}123").roles(
		 * "PROF");
		 * auth.inMemoryAuthentication().withUser("adam").password("{noop}123").roles(
		 * "etudiant");
		 */
		//ici les donnees sont stockee dans une base de donnes sql
		
		  auth.jdbcAuthentication() .dataSource(dataSource)
		  .usersByUsernameQuery("select username,password,actived from user where username = ? "
		  )
		  .authoritiesByUsernameQuery("select user_username,roles_role from user_role where user_username = ?"
		  ).passwordEncoder(encodepwd());
		 
	}
	@Bean
	public BCryptPasswordEncoder encodepwd() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
			.authorizeRequests()
				.anyRequest()
					.authenticated()
						.and()
							.formLogin()
								.loginPage("/login")
									.permitAll()
									.defaultSuccessUrl("/index.html");
		
	}

}
