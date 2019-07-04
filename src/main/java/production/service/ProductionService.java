/*
 * Copyright (c) 2018
 * BES Software Corporation
 *
 * All rights reserved.
 *
 * Revision History:
 *                                Modification       Tracking
 * Author (Email ID)              Date               Number               Description
 * -------------------------------------------------------------------------------------------
 * bing.li                        2018 M03 23                                 Initial Version
 */
package production.service;

import production.bean.Production;
import production.bean.ProductionProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import production.util.ProductionReader;

@Component
public class ProductionService {

    @Autowired
    private ProductionProperties productionProperties;

    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    private ProductionReader reader = new ProductionReader();


    public Production getProductionInfo() {
        return reader.contentToProduction(resourceLoader.getResource(productionProperties.getDescribeFile())
                , productionProperties.getName());
    }
}
