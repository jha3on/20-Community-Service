package app.community.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class ApacheTilesConfiguration {

    @Bean
    public TilesConfigurer tilesConfigurer() {
        final TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions("/WEB-INF/tiles/configuration.xml");
        configurer.setCheckRefresh(true);

        return configurer;
    }

    @Bean
    public UrlBasedViewResolver tilesViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        resolver.setOrder(1);

        return resolver;
    }
}