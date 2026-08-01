package config;

import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import controller.EmployeeController;

@ApplicationPath("/api")
public class MyAppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(EmployeeController.class);
    }
}
