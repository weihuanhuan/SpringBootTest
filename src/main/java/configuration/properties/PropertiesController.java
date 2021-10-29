package configuration.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PropertiesController {

    @Autowired
    private ServerInfoList serverInfoList;

    @GetMapping("/properties")
    @ResponseBody
    public String hello() {
        return serverInfoList.toString();
    }
}
