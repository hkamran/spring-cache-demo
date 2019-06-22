package com.hkamran.spring.cache.demo.models;

import java.util.List;

public class Template {
	
	public Sheet sheet;
	public Integer id;
	public List<Function> functions;
	
	public Template(Integer id) {
		this.id = id;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
