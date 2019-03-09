package com.siva.notes.NotesApplication;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.siva.notes.model.Note;
import com.siva.notes.model.User;
import com.siva.notes.repository.NoteRepository;
import com.siva.notes.repository.UserRepository;

@SpringBootApplication
/*
 * ( exclude = { org.springframework.boot.autoconfigure.security.servlet.
 * SecurityAutoConfiguration.class })
 */
 
@Configuration
@ComponentScan("com.siva.notes.*")
@EntityScan("com.siva.notes.model")
@EnableJpaRepositories("com.siva.notes.repository") 
@EnableWebMvc
public class NotesApplication extends AbstractSecurityWebApplicationInitializer implements CommandLineRunner, WebMvcConfigurer {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NoteRepository noteRepository;
	


	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}
	
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method userstub
		logger.info("USER REPO RUN");
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		encoder.encode("password");
		User siva = new User("Siva", "sivareddy", "test@test.com", encoder.encode("password"));
		User reddy = new User("Reddy", "sivareddy1", "test1@test1.com", encoder.encode("password"));
		siva.setCreatorId(0);
		siva.setModifierId(0);
		siva.setDeleted(false);
		siva.setCts(new Timestamp(System.currentTimeMillis()));
		siva.setMts(new Timestamp(System.currentTimeMillis()));
		
		reddy.setCreatorId(0);
		reddy.setModifierId(0);
		reddy.setDeleted(false);
		reddy.setCts(new Timestamp(System.currentTimeMillis()));
		reddy.setMts(new Timestamp(System.currentTimeMillis()));
		
		Note note1  = new Note("ABcgxvc", "description");
		note1.setCreatorId(1);
		
		Note note2 = new Note("fkjgnkh", "description");
		note2.setCreatorId(2);
		noteRepository.save(note1);
		noteRepository.save(note2);
		logger.info("insertiing" + userRepository.save(siva) + userRepository.save(reddy));
		
	}
	
	@Autowired
	   private ApplicationContext applicationContext;

	   /*
	    * STEP 1 - Create SpringResourceTemplateResolver
	    * */
	   @Bean
	   public SpringResourceTemplateResolver templateResolver() {
	      SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	      templateResolver.setApplicationContext(applicationContext);
	      templateResolver.setPrefix("/views/");
//	      templateResolver.setPrefix("/templates/");
	      templateResolver.setSuffix(".html");
	      return templateResolver;
	   }

	   /*
	    * STEP 2 - Create SpringTemplateEngine
	    * */
	   @Bean
	   public SpringTemplateEngine templateEngine() {
	      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	      templateEngine.setTemplateResolver(templateResolver());
	      templateEngine.setEnableSpringELCompiler(true);
	      return templateEngine;
	   }

	   /*
	    * STEP 3 - Register ThymeleafViewResolver
	    * */
	   @Override
	   public void configureViewResolvers(ViewResolverRegistry registry) {
	      ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	      resolver.setTemplateEngine(templateEngine());
	      registry.viewResolver(resolver);
	   }
  
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/resources/**")
              .setCachePeriod(0);
      
  }
	
	

}
