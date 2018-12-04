package security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/templates/profile").setViewName("templates/profile");
        registry.addViewController("/templates/").setViewName("templates/home");
        registry.addViewController("/templates/cart").setViewName("templates/cart");
        registry.addViewController("/templates/login").setViewName("templates/login");
    }

}