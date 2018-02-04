package es.symbioserver.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebSecurity( debug = true )
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicAuthenticationPoint basicAuthenticationPoint;
    
	@Autowired
	private DataSource dataSource;

   

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
         http.csrf().disable();
         http.httpBasic().and().authorizeRequests()
         
         //SWAGGER CONSOLE
         ///////////////////////////////////////////////////////////////////////////////////
         .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
         .antMatchers(HttpMethod.GET, "/swagger-resources").permitAll()
         .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
         .antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
         .antMatchers(HttpMethod.GET, "/configuration/**").permitAll()
         ///////////////////////////////////////////////////////////////////////////////////
         
         //OPEN FUNCTIONALITIES
         ///////////////////////////////////////////////////////////////////////////////////
         .antMatchers(HttpMethod.GET, "/postService/getPostById/**").permitAll()
         .antMatchers(HttpMethod.GET, "/postService/getPostsListByCriteria/**").permitAll()
         .antMatchers(HttpMethod.POST, "/userService/insertUser").permitAll()
         ///////////////////////////////////////////////////////////////////////////////////
         
         //AUTHENTICATE FUNCTIONALITIES
         ///////////////////////////////////////////////////////////////////////////////////	
         .antMatchers(HttpMethod.POST, "/postService/insertPost").authenticated()
         .antMatchers(HttpMethod.PUT, "/postService/updatePost/**").authenticated()
         .antMatchers(HttpMethod.DELETE, "/postService/deletePost/**").authenticated()
         .antMatchers(HttpMethod.GET, "/postService/getPostByUID/**").authenticated()
         .antMatchers(HttpMethod.PUT, "/userService/modifyUser/**").authenticated()
         .antMatchers(HttpMethod.GET, "/userService/getUserByUID/**").authenticated()
         .anyRequest().authenticated(); 
         ///////////////////////////////////////////////////////////////////////////////////
    	
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
    	auth.jdbcAuthentication().dataSource(dataSource)
    	.authoritiesByUsernameQuery("select name, role FROM user where name=?")
    	.usersByUsernameQuery("select name, password, enabled FROM user where name=?");
    	
    	/*
    	auth.inMemoryAuthentication().withUser("chandana").password("chandana").roles("USER");
    	*/
    }
}
