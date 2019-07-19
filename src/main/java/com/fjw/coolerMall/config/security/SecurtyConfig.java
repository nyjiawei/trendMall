package com.fjw.coolerMall.config.security;



public class SecurtyConfig {

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("password").roles("USER").and()
			.withUser("admin").password("password").roles("USER","ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/index","/product","about").permitAll()
			.antMatchers("/order/**").hasAnyAuthority("USER")
			.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
			.anyRequest().authenticated()
		.and().formLogin()
			.loginPage("/login").failureUrl("/login?errr")
			.defaultSuccessUrl("/index")
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
		.and().logout()
			.logoutUrl("/logout").logoutSuccessUrl("/login?loggedOut")
			.invalidateHttpSession(true).deleteCookies("JSESSIONID")
			.permitAll()
		.and().csrf().disable();
	}*/

}
