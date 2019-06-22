package com.hkamran.spring.cache.demo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class Main {

	public static final String CACHE_STORE_FUNCTIONS = "functions";
	public static final String CACHE_STORE_SHEETS = "sheets";
	public static final String PARSE_CACHE_MANAGER = "parseCacheManager";

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

    @Bean(name=PARSE_CACHE_MANAGER)
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
        		new ConcurrentMapCache(CACHE_STORE_SHEETS),
        		new ConcurrentMapCache(CACHE_STORE_FUNCTIONS)
        ));
        return cacheManager;
    }
	
}
