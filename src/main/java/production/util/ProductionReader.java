package production.util;

import production.bean.Production;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.io.Resource;

/**
 * Created by JasonFitch on 7/4/2019.
 */
public class ProductionReader {

    public static final String DEFAULT_SEPARATOR = "---";

    private String separator = DEFAULT_SEPARATOR;
    private Map<String, Production> cache = new HashMap<>();
    private Production emptyProduction = Production.EMPTY;

    public Production contentToProduction(String name, Resource resource) {
        return contentToProduction(name, resource, separator);
    }

    public Production contentToProduction(String name, Resource resource, String separator) {
        Production production = cache.get(name);
        if (production != null) {
            return production;
        }

        boolean notFound = true;
        String name_en = "";
        String name_zh = "";
        String desc = "";
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()
                    , Charset.forName("UTF-8")));
            while (true) {
                name_en = bufferedReader.readLine();
                name_zh = bufferedReader.readLine();
                desc = bufferedReader.readLine();
                String line = bufferedReader.readLine();

                if (line == null || !line.equals(this.separator)) {
                    break;
                }
                if (name_en.equalsIgnoreCase(name)) {
                    notFound = false;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (production == null && !notFound)
            cache.putIfAbsent(name, new Production(name_en, name_zh, desc));
        return cache.getOrDefault(name, emptyProduction);
    }
}
