package com.example.demo.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.catalina.Context;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

/**
 * Configuración de Tomcat
 */
@Configuration
public class TomcatConfig {

    /**
     * Configuración del contenedor servlet de Tomcat
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        // Conector HTTP adicional en 8080
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setPort(8080);
        factory.addAdditionalTomcatConnectors(connector);

        return factory;
    }
    
}
/* 
 
@Configuration
public class TomcatConfig {

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                // Configura que todo el tráfico debe ser HTTPS
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };

        // Conector HTTP adicional en 8080 que redirige automáticamente a HTTPS
        Connector httpConnector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        httpConnector.setScheme("http");
        httpConnector.setPort(8080);
        httpConnector.setSecure(false);
        httpConnector.setRedirectPort(8443);

        factory.addAdditionalTomcatConnectors(httpConnector);
        return factory;
    }
}
*/
