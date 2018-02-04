package es.symbioserver;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2

public class Application {
	

	 public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
	 }
	 
	 
	  /**
	  * Launch Swagger2 portal (url access: http://localhost:8080/swagger-ui.html)
	  * @return
	  */
	 //SWAGGER_2 CONFIGURATION
	 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  @Bean
	  public Docket newsApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.select()
	        		.apis(RequestHandlerSelectors.basePackage("es.symbioserver.controller"))
	                .paths(regex("(/postService/(insertPost|updatePost|deletePost|getPostById|getPostByUID|getPostsListByCriteria)(/.*|)|/userService/(insertUser|modifyUser|getUserByUID)(/.*|))"))
	                .build()
	                .apiInfo(apiInfo());
	  }
	 
	 
	 
	 
	  private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Spring REST Symbio Post Service  API")
	                .description("Secure Spring REST Symbio Post Service API with Swagger")
	                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
	                .contact("Joan Alcaraz")
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
	                .version("2.0")
	                .build();
	  }
	  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
}
