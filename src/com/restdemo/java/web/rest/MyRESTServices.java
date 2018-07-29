package com.restdemo.java.web.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;

import com.restdemo.java.bean.Product;

@ApplicationPath("restservices")
public class MyRESTServices extends ResourceConfig {
	
	public static Map<Integer,Product> catalogDB;
	
    public MyRESTServices() {
    	
    	catalogDB = new HashMap<>();
    }
    
    
    public static Map<Integer,Product> getProductCatalog(){
    	return catalogDB;
    }
}
