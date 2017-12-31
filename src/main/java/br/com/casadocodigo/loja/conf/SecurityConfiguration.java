package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.daos.UsuarioDAO;

//OBS: no curso, usa uma versão anterior, e por isso usa a @EnableWebMvcSecurity (que agora está deprecated)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// é importante sempre colocar as regras mais restritas antes
		http.authorizeRequests()
		.antMatchers("/produtos/form").hasRole("ADMIN")
		.antMatchers("/carrinho/**").permitAll()
		.antMatchers("/pagamento/**").permitAll()
		.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
		.antMatchers("/resources/**").permitAll() 
		.antMatchers("/").permitAll()
		.antMatchers("/url-magica-maluca-akdjfaklsjd9a8e45j4494j5iajemm3kh6gb53p56i5uj43jmjdf8as5j").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		System.out.println(" >>>-------  finalizou configure security ---------------------<<<");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
}
