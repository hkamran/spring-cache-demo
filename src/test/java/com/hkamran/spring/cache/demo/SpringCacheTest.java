package com.hkamran.spring.cache.demo;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hkamran.spring.cache.demo.models.Template;
import com.hkamran.spring.cache.demo.parser.TemplateParser;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCacheTest {

	@Autowired
	TemplateParser templateParser;
	
	@Test
	public void testCacheWithAnnotation() {
		Integer id = new Random().nextInt();
		Template templateOne = templateParser.parseWithAnnotation(id);
		Template templateTwo = templateParser.parseWithAnnotation(id);
		
		Assert.assertEquals(
				System.identityHashCode(templateOne.sheet), 
				System.identityHashCode(templateTwo.sheet));
		
		Assert.assertEquals(templateOne.sheet.id, templateTwo.sheet.id);
		
		Assert.assertEquals(
				System.identityHashCode(templateOne.functions),
				System.identityHashCode(templateTwo.functions));
		
	}
	
	@Test
	public void testCacheWithOutAnnotation() {
		Integer id = new Random().nextInt();
		Template templateOne = templateParser.parseWithOutAnnotation(id);
		Template templateTwo = templateParser.parseWithOutAnnotation(id);
		
		Assert.assertEquals(
				System.identityHashCode(templateOne.sheet), 
				System.identityHashCode(templateTwo.sheet));
		
		Assert.assertEquals(templateOne.sheet.id ,templateTwo.sheet.id);
		
		Assert.assertEquals(
				System.identityHashCode(templateOne.functions),
				System.identityHashCode(templateTwo.functions));
		
	}
	

}
