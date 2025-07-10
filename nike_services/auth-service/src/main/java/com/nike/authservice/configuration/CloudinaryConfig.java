package com.nike.authservice.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("${CLOUD_NAME:NOT_SET}")
    private String cloudName;

    @Value("${API_KEY:NOT_SET}")
    private String apiKey;

    @Value("${API_SECRET:NOT_SET}")
    private String apiSecret;

    @Bean
    public Cloudinary configKey() {
        Map<String, String> config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        Cloudinary cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
