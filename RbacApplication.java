package com.example.rbac.rbac;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = "com.example.rbac.rbac.model")
@EnableJpaRepositories(basePackages = "com.example.rbac.rbac.repository")
public class RbacApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacApplication.class, args);
	}
	@PostConstruct
	public void checkEntities() {
		System.out.println("Managed Entities: ");
		DefaultListableBeanFactory context = new DefaultListableBeanFactory();
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}
}
