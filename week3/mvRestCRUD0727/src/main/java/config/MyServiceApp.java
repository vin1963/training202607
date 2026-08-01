package config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import controller.Hello;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/srv")
public class MyServiceApp extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		return new HashSet();
	}
}
