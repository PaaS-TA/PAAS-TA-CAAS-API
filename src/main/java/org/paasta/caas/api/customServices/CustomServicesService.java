package org.paasta.caas.api.customServices;

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
     * Services 목록을 조회한다.
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
     * Services 상세 정보를 조회한다.
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

        return (CustomServices) commonService.setResultModel(commonService.setResultObject(resultMap, CustomServices.class), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Services YAML을 조회한다.
     *
     * @param namespace   the namespace
     * @param serviceName the service name
     * @param resultMap   the result map
     * @return the custom services yaml
     */
    CustomServices getCustomServicesYaml(String namespace, String serviceName, HashMap resultMap) {
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListServicesGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", serviceName), HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML);

        //noinspection unchecked
        resultMap.put("sourceTypeYaml", resultString);

        return (CustomServices) commonService.setResultModel(commonService.setResultObject(resultMap, CustomServices.class), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Services를 생성한다.
     *
     * @param namespace       the namespace
     * @param service         the Services
     * @return                 return is succeeded
     */
    public Map<?,?> createServicesYaml(String namespace, Object service, HashMap resultMap) {
        System.out.println("namespace:::::" + namespace );
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListServicesCreate()
                        .replace("{namespace}", namespace), HttpMethod.POST, service, String.class, Constants.ACCEPT_TYPE_YAML);
        resultMap.put("sourceTypeYaml", resultString);
        return resultMap;
    }

    /**
     * Services를 제거한다.
     *
     * @param namespace       the namespace
     * @param name             the Services name
     * @return                 return is succeeded
     */
    public Map<?,?> deleteServicesYaml(String namespace, String name, HashMap resultMap) {
        System.out.println("namespace:::::" + namespace );
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListServicesDelete()
                        .replace("{namespace}", namespace).replace("{name}", name), HttpMethod.DELETE, null, String.class, Constants.ACCEPT_TYPE_YAML);
        resultMap.put("sourceTypeYaml", resultString);
        return resultMap;
    }
}
