package org.metrodataacademy.finalproject.serverapp.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.Cloudinary;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "easyclass",
                "api_key", "294599962564915",
                "api_secret", "FBQ4m8cQOjkx1g9Cb18lu9xRMKw"));
    }
}