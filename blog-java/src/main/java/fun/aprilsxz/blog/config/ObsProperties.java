package fun.aprilsxz.blog.config;

import com.obs.services.ObsClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(
        prefix = "huawei.obs"
)
public class ObsProperties {

    private String endPoint;

    private String ak;

    private String sk;

    private String bucketName;
}
