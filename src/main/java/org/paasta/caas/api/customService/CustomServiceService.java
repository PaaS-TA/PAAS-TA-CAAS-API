package org.paasta.caas.api.customService;

import com.google.gson.Gson;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom Service Service 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.8.09
 */
@Service
public class CustomServiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomServiceService.class);
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Custom service service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public CustomServiceService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * Gets custom service list.
     *
     * @param namespace the namespace
     * @return the custom service list
     */
    CustomServiceList getCustomServiceList(String namespace) {
        // TODO :: REMOVE
        namespace = Constants.NAMESPACE_NAME;

        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListServicesListUrl().replace("{namespace}", namespace), HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (CustomServiceList) commonService.setResultModel(new Gson().fromJson(new Gson().toJson(resultMap), CustomServiceList.class), Constants.RESULT_STATUS_SUCCESS, "");
    }

    /**
     * Gets custom service.
     *
     * @param namespace   the namespace
     * @param serviceName the service name
     * @return the custom service
     */
    CustomService getCustomService(String namespace, String serviceName) {
        // TODO :: REMOVE
        namespace = Constants.NAMESPACE_NAME;
        serviceName = Constants.SERVICE_NAME;

        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListServicesGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", serviceName), HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (CustomService) commonService.setResultModel(new Gson().fromJson(new Gson().toJson(resultMap), CustomService.class), Constants.RESULT_STATUS_SUCCESS, "");
    }

}
