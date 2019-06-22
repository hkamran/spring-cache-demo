package com.hkamran.spring.cache.demo.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hkamran.spring.cache.demo.models.Template;


@Component
public class TemplateParser {

	@Autowired
	SheetParser sheetParser;
	
	@Autowired
	FunctionParser functionParser;
	
	public Template parseWithAnnotation(Integer id) {
		Template template = new Template(id);
		template.sheet = sheetParser.parseWithAnnotation(template.id);
		template.functions = functionParser.parseWithAnnotation(template.id);
		return template;
	}
	
	public Template parseWithOutAnnotation(Integer id) {
		Template template = new Template(id);
		template.sheet = sheetParser.parseWithOutAnnotation(template.id);
		template.functions = functionParser.parseWithOutAnnotation(template.id);
		return template;
	}
	
}
