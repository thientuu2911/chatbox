package com.tma.chatbox;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@Log4j2
public class ChatboxApplication {
	private static final List<String> ORIGINS = Arrays.asList("http://localhost:4200");

	private static final List<String> METHODS = Arrays.asList("GET", "PUT", "POST", "DELETE");

	private static final List<String> HEADERS = Arrays.asList("Content-Type", "Authorization", "Accept-Language");

	private static final String CORS = "/**";

	@Value("${app.firebase-configuration-file}")
	private String firebaseConfigPath;

	public static void main(String[] args) {
		SpringApplication.run(ChatboxApplication.class, args);
	}
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(ORIGINS);
		configuration.setAllowedMethods(METHODS);
		configuration.setAllowedHeaders(HEADERS);
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(CORS, configuration);
		return source;
	}

	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		log.info("Start init");
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions.builder().setCredentials(googleCredentials).build();
		FirebaseApp firebaseApp = FirebaseApp.initializeApp(firebaseOptions);
		log.info("Firebase application has been initialized");

		return FirebaseMessaging.getInstance(firebaseApp);
	}
}
