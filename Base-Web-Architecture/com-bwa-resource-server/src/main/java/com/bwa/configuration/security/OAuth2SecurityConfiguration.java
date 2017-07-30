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
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

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

       /* http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/home").access("hasRole('ROLE_USER')")
                .antMatchers("/admin*//**").access("hasRole('ROLE_ADMIN')")
		 .antMatchers("/api*//**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_API')")
		 .and()
		 .formLogin()
		 .loginProcessingUrl("/login")
		 .loginPage("/loginPage")
		 .failureUrl("/loginPage?error")
		 .defaultSuccessUrl("/home")
		 .usernameParameter("username")
		 .passwordParameter("password")
		 .and()
		 .exceptionHandling()
		 .accessDeniedPage("/Access_Denied").and()
		 .addFilterBefore(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		 */
		http.addFilterBefore(customUsernamePasswordAuthenticationFilter()
				,UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().authenticationEntryPoint(urlAuthenticationEntryPoint());
		/*http
				.formLogin()
				.loginPage("/login")
		.isCustomLoginPage();*/
//		http.authorizeRequests().antMatchers("/login").permitAll();
//				.antMatchers("/oauth/token/revokeById*").permitAll()
//				.antMatchers("/tokens*").permitAll()
		http.authorizeRequests().anyRequest().authenticated()
				.and().formLogin().permitAll()
				.and().csrf().disable();
//        http.addFilterBefore(customUsernamePasswordAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public UsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter()
			throws Exception {
		UsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();
        /*authFilter
                .setAuthenticationManager(authenticationManagerBean());
*/
        /*List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
        authenticationProviderList.add(authenticationProvider());
        AuthenticationManager authenticationManager = new ProviderManager(authenticationProviderList);
        */
		authFilter.setUsernameParameter("username");
		authFilter.setPasswordParameter("password");
        authFilter.setRequiresAuthenticationRequestMatcher(
				new AntPathRequestMatcher("/login","POST"));
		authFilter.setAuthenticationSuccessHandler(new CustomLoginSuccessHandler());
		authFilter.setAuthenticationFailureHandler(new CustomLoginFailureHandler());
		authFilter.setAuthenticationManager(authenticationManager());

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

/*
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}
	
	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}*/

}
