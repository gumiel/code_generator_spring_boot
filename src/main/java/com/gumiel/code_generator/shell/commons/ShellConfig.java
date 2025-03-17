package com.gumiel.code_generator.shell.commons;

import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class ShellConfig {

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("PIMPON_SHELL:>");
    }
}
