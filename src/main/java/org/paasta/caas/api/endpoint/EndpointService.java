package org.paasta.caas.api.endpoint;

import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.paasta.caas.api.customService.CustomServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Endpoint Service 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Service
public class EndpointService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomServiceService.class);
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Endpoint service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public EndpointService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * Gets endpoint list.
     *
     * @param namespace the namespace
     * @return the endpoint list
     */
    EndpointList getEndpointList(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListEndpointsListUrl()
                        .replace("{namespace}", namespace), HttpMethod.GET, null, Map.class);

        return (EndpointList) commonService.setResultModel(commonService.fromJson(commonService.toJson(resultMap),
                EndpointList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Gets custom service.
     *
     * @param namespace   the namespace
     * @param serviceName the service name
     * @return the custom service
     */
    Endpoint getEndpoint(String namespace, String serviceName) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListEndpointsGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", serviceName), HttpMethod.GET, null, Map.class);

        return (Endpoint) commonService.setResultModel(commonService.fromJson(commonService.toJson(resultMap),
                Endpoint.class), Constants.RESULT_STATUS_SUCCESS);
    }

}
