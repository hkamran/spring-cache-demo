package com.hkamran.spring.cache.demo;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.hibernate.EhCache;

@SpringBootApplication
@EnableCaching
public class Main {

	public static final String CACHE_STORE_FUNCTIONS = "functions";
	public static final String CACHE_STORE_SHEETS = "sheets";
	public static final String PARSE_CACHE_MANAGER = "parseCacheManager";
	public static final String PARSE_EHCACHE_MANAGER = "ehCacheCacheManager";

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Autowired
	EhCacheManagerFactoryBean ehFactory;
	
    @Bean(name=PARSE_CACHE_MANAGER)
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        
        cacheManager.setCaches(Arrays.asList(
        		new ConcurrentMapCache(CACHE_STORE_SHEETS),
        		new EhCacheCache(ehFactory.getObject().getEhcache(CACHE_STORE_FUNCTIONS))
        ));
        return cacheManager;
    }

    @Bean
	public EhCacheManagerFactoryBean ehCacheCacheManagerFactory() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}

    
	
}
