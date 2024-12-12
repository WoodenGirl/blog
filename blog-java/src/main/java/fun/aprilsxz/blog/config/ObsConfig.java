package fun.aprilsxz.blog.config;

import com.obs.services.ObsClient;
import com.obs.services.model.CopyObjectRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(ObsProperties.class)
public class ObsConfig {
    @Resource
    ObsProperties obsProperties;

    @Bean
    public ObsClient obsClient(){
        return new ObsClient(obsProperties.getAk(),obsProperties.getSk(),obsProperties.getEndPoint());
    }
}
