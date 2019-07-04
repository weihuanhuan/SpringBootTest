package production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import production.service.ProductionService;

@Controller
public class ProductionController {

    @Autowired
    private ProductionService productionService;

    @GetMapping("/production")
    @ResponseBody
    public String hello() {
        return this.productionService.getProductionInfo().toString();
    }
}
