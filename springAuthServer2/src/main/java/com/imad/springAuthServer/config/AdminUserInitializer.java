package com.imad.springAuthServer.config;
import com.imad.springAuthServer.Repo.clientRepo;
import com.imad.springAuthServer.entities.client;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AdminUserInitializer  implements CommandLineRunner {

    private clientRepo clientRepository;


    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if the admin user already exists
        if (!clientRepository.existsByUserName("admin")) {
            // Create the admin user
            client admin = client.builder()
                    .userName("admin")
                    .password(passwordEncoder.encode("admin")) // Encode the password
                    .authoroties(List.of("ADMIN"))

                    .build();


            clientRepository.save(admin);
            System.out.println("Default admin user created successfully.");
        } else {
            System.out.println("Default admin user already exists.");
        }
    }
}
