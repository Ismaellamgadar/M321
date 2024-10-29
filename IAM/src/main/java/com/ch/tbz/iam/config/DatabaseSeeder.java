package com.ch.tbz.iam.config;

import com.ch.tbz.iam.entity.AppUser;
import com.ch.tbz.iam.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            // Check if admin user exists, if not, create one
            if (userRepository.findByUsername("admin").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword("password");
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println("Admin user created: " + userRepository.findByUsername("admin").isPresent());
            }
        };
    }
}
