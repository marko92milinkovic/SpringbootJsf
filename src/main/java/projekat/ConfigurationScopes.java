/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author marko
 */
@org.springframework.context.annotation.Configuration
public class ConfigurationScopes {

    @Bean
    public CustomScopeConfigurer getCustomScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("view", new SpringViewJsfScope());

        configurer.setScopes(scopes);
        return configurer;
    }
}
