package org.paasta.caas.api.customServices;

import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Custom Services Service 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.8.09
 */
@Service
public class CustomServicesService {

    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Custom services service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public CustomServicesService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }


    /**
     * Gets custom services list.
     *
     * @param namespace the namespace
     * @return the custom services list
     */
    CustomServicesList getCustomServicesList(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListServicesListUrl()
                        .replace("{namespace}", namespace), HttpMethod.GET, null, Map.class);

        return (CustomServicesList) commonService.setResultModel(commonService.setResultObject(resultMap, CustomServicesList.class), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Gets custom services.
     *
     * @param namespace   the namespace
     * @param serviceName the service name
     * @return the custom services
     */
    CustomServices getCustomServices(String namespace, String serviceName) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListServicesGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", serviceName), HttpMethod.GET, null, Map.class);

        //noinspection unchecked
        resultMap.put("source", new LinkedHashMap(resultMap));

        return (CustomServices) commonService.setResultModel(commonService.setResultObject(resultMap, CustomServices.class), Constants.RESULT_STATUS_SUCCESS);
    }

}
