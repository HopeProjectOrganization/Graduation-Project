package com.GraduationProject.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class Notification {

    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount;

            File file = new File("src/main/resources/firebase-config.json");
            if (file.exists()) {
                serviceAccount = new FileInputStream(file);
                System.out.println("🧪 Loaded Firebase credentials from local file.");
            } else {
                String firebaseConfig = System.getenv("FIREBASE_CONFIG");
                if (firebaseConfig == null || firebaseConfig.isEmpty()) {
                    throw new IllegalStateException(
                            "FIREBASE_CONFIG is not set—needed for deployment!"
                    );
                }
                serviceAccount = new ByteArrayInputStream(
                        firebaseConfig.getBytes(StandardCharsets.UTF_8)
                );
                System.out.println("🚀 Loaded Firebase credentials from env var.");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase initialized successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }
}
