package org.paasta.caas.api.endpoints;

import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Endpoints Service 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Service
public class EndpointsService {

    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Endpoints service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public EndpointsService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }


    /**
     * Endpoints 목록을 조회한다.
     *
     * @param namespace the namespace
     * @return the endpoints list
     */
    EndpointsList getEndpointsList(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListEndpointsListUrl()
                        .replace("{namespace}", namespace), HttpMethod.GET, null, Map.class);

        return (EndpointsList) commonService.setResultModel(commonService.setResultObject(resultMap, EndpointsList.class), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Endpoints 상세 정보를 조회한다.
     *
     * @param namespace   the namespace
     * @param serviceName the service name
     * @return the endpoints
     */
    Endpoints getEndpoints(String namespace, String serviceName) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListEndpointsGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", serviceName), HttpMethod.GET, null, Map.class);

        return (Endpoints) commonService.setResultModel(commonService.setResultObject(resultMap, Endpoints.class), Constants.RESULT_STATUS_SUCCESS);
    }

}
