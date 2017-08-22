package com.bwa.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired DataSource dataSource;
	//	@Autowired private ClientDetailsService clientDetailsService;
//	@Autowired private AuthenticationServiceImpl authenticationServiceImpl;

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder auth) throws Exception {

		/* work with dataSource
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select username, role from user_roles where username=?");

		 work with user detail service
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		auth.userDetailsService(authenticationService).passwordEncoder(encoder);
        */

		 /*todo uncomment this line for oauth
		auth.userDetailsService(authenticationService);*/


		auth.authenticationProvider(authenticationProvider());
		/*for in memory
		auth.inMemoryAuthentication()
        .withUser("bill").password("abc123").roles("ADMIN").and()
        .withUser("bob").password("abc123").roles("USER");*/
	}
    /*@Bean
    public AbstractUserDetailsAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(authenticationServiceImpl);
//        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }*/

	@Bean
	public AbstractUserDetailsAuthenticationProvider authenticationProvider() {
		return new MultiFieldAuthenticationProvider();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.addFilterBefore(customUsernamePasswordAuthenticationFilter()
				,UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().authenticationEntryPoint(urlAuthenticationEntryPoint());
		http.sessionManagement().maximumSessions(10);
		http.sessionManagement()
				.sessionFixation().migrateSession();
		/*http
				.formLogin()
				.loginPage("/login").permitAll();*/
//		.and().rememberMe().tokenRepository(persistentTokenRepository());

		http.authorizeRequests().antMatchers("/hasLoggedIn").hasAuthority("PRV_PERSIST_SESSION")
				.antMatchers("/fetchNavBar").hasAuthority("PRV_FETCH_NAV_MENU")
				.anyRequest().authenticated()
				.and().formLogin().permitAll()
				.and().csrf().disable();
	}

	@Bean
	public UsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter()
			throws Exception {
		UsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();

		authFilter.setUsernameParameter("username");
		authFilter.setPasswordParameter("password");
        authFilter.setRequiresAuthenticationRequestMatcher(
				new AntPathRequestMatcher("/login","POST"));
		authFilter.setAuthenticationSuccessHandler(new CustomLoginSuccessHandler());
		authFilter.setAuthenticationFailureHandler(new CustomLoginFailureHandler());
		authFilter.setAuthenticationManager(authenticationManager());
		authFilter.setSessionAuthenticationStrategy(sas());
		return authFilter;
	}

	@Bean
	public LoginUrlAuthenticationEntryPoint urlAuthenticationEntryPoint(){
		LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint =
				new CustomLoginUrlAuthenticationEntryPoint("/login");
		return loginUrlAuthenticationEntryPoint;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy(){
		ConcurrentSessionControlAuthenticationStrategy cscas=new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
		cscas.setMaximumSessions(10);
		cscas.setExceptionIfMaximumExceeded(true);
		return cscas;
	}

	@Bean
	public CompositeSessionAuthenticationStrategy sas(){
		SessionFixationProtectionStrategy sfps=new SessionFixationProtectionStrategy();
		RegisterSessionAuthenticationStrategy rsas=new RegisterSessionAuthenticationStrategy(sessionRegistry());

		List<SessionAuthenticationStrategy> sasList=new ArrayList<SessionAuthenticationStrategy>();
		sasList.add(concurrentSessionControlAuthenticationStrategy());
		sasList.add(sfps);
		sasList.add(rsas);

		CompositeSessionAuthenticationStrategy sas= new CompositeSessionAuthenticationStrategy(sasList);
		return sas;
	}

	@Bean
	public SessionRegistry sessionRegistry(){
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	/*@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		final JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;

	}*/
}
