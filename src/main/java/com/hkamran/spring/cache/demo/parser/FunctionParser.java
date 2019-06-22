package com.hkamran.spring.cache.demo.parser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hkamran.spring.cache.demo.Main;
import com.hkamran.spring.cache.demo.models.Function;

@Component
public class FunctionParser {
	
	@Autowired
	@Qualifier(Main.PARSE_CACHE_MANAGER)
	private CacheManager cacheManager;
	
	//https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/cache.html#cache-spel-context
	
	@Cacheable(cacheManager=Main.PARSE_CACHE_MANAGER, cacheNames=Main.CACHE_STORE_FUNCTIONS,  key="#root.args[0]")
	public List<Function> parseWithAnnotation(Integer id) {
		List<Function> functions = new ArrayList<Function>();
		functions.add(new Function(id));
		return functions;
	}

	public List<Function> parseWithOutAnnotation(Integer id) {
		Cache cache = cacheManager.getCache(Main.CACHE_STORE_FUNCTIONS);

		ValueWrapper cachedFunctions = cache.get(id);
		
		if (cachedFunctions != null) { 
			List<Function> functions = (List<Function>) cache.get(id).get();
			return functions;
		} else {
			List<Function> functions = new ArrayList<Function>();
			functions.add(new Function(id));
			cache.put(id, functions);
			return functions;
		}
	}
}
