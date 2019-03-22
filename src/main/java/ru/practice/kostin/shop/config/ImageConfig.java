package ru.practice.kostin.shop.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "shop.image")
public class ImageConfig {
    private String directoryPath;
    private String prefix;
    private String placeholderName;
}
