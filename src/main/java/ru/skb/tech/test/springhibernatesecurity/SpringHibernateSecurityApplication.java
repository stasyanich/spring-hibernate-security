package ru.skb.tech.test.springhibernatesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry //включение возможности повторной отправки сообщений
public class SpringHibernateSecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringHibernateSecurityApplication.class, args);
	}
}
