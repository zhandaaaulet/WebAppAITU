import controllers.AuthorizationController;
import controllers.ProductController;
import controllers.UserController;
import filters.CorsFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> hs = new HashSet<>();
        hs.add(UserController.class);
        hs.add(AuthorizationController.class);
        hs.add(CorsFilter.class);
        hs.add(ProductController.class);
        return hs;
    }
}
