package in.rahul.main.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.rahul.main.Service.SuccessHandler;

@Configuration
public class SecurityConfig {
	@Autowired
	private UserDetailsService myUserDetailService;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

		return security.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth

				.requestMatchers("/home", "/register", "/saveUser").permitAll()

				.requestMatchers("/admin").hasRole("ADMIN")

				.requestMatchers("/addBook").hasRole("ADMIN")

				.requestMatchers("/updateBook").hasRole("ADMIN")

				.requestMatchers("/deleteBook").hasRole("ADMIN")

				.requestMatchers("/deleteSuccess").hasRole("ADMIN")

				.requestMatchers("/notFound").hasRole("ADMIN")

				.requestMatchers("/user").hasRole("USER").requestMatchers("/borrow/**").hasRole("USER")
				.requestMatchers("/borrow/**").hasRole("ADMIN")

				.anyRequest().authenticated())

				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
						// .defaultSuccessUrl("/home",true)
						.successHandler(new SuccessHandler()).permitAll())

				.logout(logout -> logout.logoutUrl("/logout")
						// .logoutSuccessUrl("/login?logout")
						.permitAll())

				.build();

	}

	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 *
	 * UserDetails user=User.builder() .username("Rahul") .password("abc")
	 * .roles("USER") .build();
	 *
	 *
	 * UserDetails admin=User.builder() .username("Rohit") .password("123")
	 * .roles("ADMIN") .build(); return new InMemoryUserDetailsManager(user,admin);
	 *
	 * }
	 */

	@Bean
	public UserDetailsService userDetailsService() {
		return myUserDetailService;
	}
}