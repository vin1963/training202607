package config;

import java.util.*;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import controller.*;

@ApplicationPath("/api")
public class MyCRUDApp extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(Hello.class);
		classes.add(ProductController.class);
		return classes;
	}
    
}
