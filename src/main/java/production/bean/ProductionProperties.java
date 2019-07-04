package production.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by JasonFitch on 7/4/2019.
 */
@Component
@ConfigurationProperties(prefix = "bes.production")
public class ProductionProperties {

    private String name;
    private String describeFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribeFile() {
        return describeFile;
    }

    public void setDescribeFile(String describeFile) {
        this.describeFile = describeFile;
    }
}
