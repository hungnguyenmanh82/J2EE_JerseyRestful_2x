package hung.com.restful2.app;

import org.glassfish.jersey.server.ResourceConfig;

public class HelloApplication extends ResourceConfig {
    public HelloApplication() {
        // Define the package which contains the service classes.
        packages("hung.com.restful2.service");
    }
}
