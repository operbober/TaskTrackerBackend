package by.tasktracker;

import by.tasktracker.utils.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	private static final String TASK_TRACKER_EMAIL = "tasktrackeremail";
	private static final String TASK_TRACKER_EMAIL_PASSWORD = "tasktrackeremailpass";

	@Bean
	public MailService mailService() {
		return new MailService(
				System.getenv(TASK_TRACKER_EMAIL),
				System.getenv(TASK_TRACKER_EMAIL_PASSWORD)
		);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public TokenStore keyStroke() {
		return new InMemoryTokenStore();
	}
}
