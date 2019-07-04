package production.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by JasonFitch on 7/4/2019.
 */
@Component
@ConfigurationProperties(prefix = "production")
public class ProductionProperties {

    private String name;
    private String describeFile;
    private String separator;

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

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
