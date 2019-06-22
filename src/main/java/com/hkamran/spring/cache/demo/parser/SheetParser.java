package com.hkamran.spring.cache.demo.parser;

import javax.annotation.Resource;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hkamran.spring.cache.demo.Main;
import com.hkamran.spring.cache.demo.models.Sheet;

@Component
public class SheetParser {
	
	@Resource(name=Main.PARSE_CACHE_MANAGER)
	private CacheManager cacheManager;
	
	//https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/cache.html#cache-spel-context
	
	@Cacheable(cacheManager=Main.PARSE_CACHE_MANAGER, cacheNames=Main.CACHE_STORE_SHEETS,  key="#root.args[0]")
	public Sheet parseWithAnnotation(Integer id) {
		Sheet sheet = new Sheet(id);
		return sheet;
	}

	public Sheet parseWithOutAnnotation(Integer id) {
		Cache cache = cacheManager.getCache(Main.CACHE_STORE_SHEETS);
		
		Sheet sheet = cache.get(id, Sheet.class);
		if (sheet == null) {
			sheet = new Sheet(id);
			cache.put(id, sheet);
		}
		return sheet;
	}
}
