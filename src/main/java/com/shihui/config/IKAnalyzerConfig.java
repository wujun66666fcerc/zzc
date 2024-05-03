package com.shihui.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.dic.Dictionary;

import java.io.IOException;

@Configuration
public class IKAnalyzerConfig {

    @Bean
    public org.wltea.analyzer.cfg.Configuration getIKAnalyzerConfig() throws IOException {
        return DefaultConfig.getInstance();
    }
}
