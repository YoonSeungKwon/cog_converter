package com.test.project.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
public class ExecutorConfig {

    @Bean
    public ForkJoinPool executorService(){
        return new ForkJoinPool(Runtime.getRuntime().availableProcessors()-1);
    }
}
