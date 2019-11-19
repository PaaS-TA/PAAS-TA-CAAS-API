package org.paasta.caas.api.clusters.namespaces;

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
 * Namespaces Service 클래스.
 *
 * @author indra
 * @version 1.0
 * @since 2018.8.01 최초작성
 */
@Service
public class NamespacesService {

    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Namespace service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public NamespacesService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * Namespaces 상세정보를 조회한다.
     *
     * @param namespace the namespaces
     * @return Namespaces the namespaces
     */
    Namespaces getNamespaces(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListNamespaceGetUrl()
                        .replace("{namespace}", namespace), HttpMethod.GET, null, Map.class);

        return (Namespaces) commonService.setResultModel(commonService.setResultObject(resultMap, Namespaces.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * ResourceQuota 목록을 조회한다.
     *
     * @param namespace the namespaces
     * @return ResourceQuotaList the ResourceQuotaList
     */
    ResourceQuotaList getResourceQuotaList(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListResourceQuotasListUrl()
                        .replace("{namespace}", namespace), HttpMethod.GET, null, Map.class);

        return (ResourceQuotaList) commonService.setResultModel(commonService.setResultObject(resultMap, ResourceQuotaList.class), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * NameSpace를 생성한다.
     *
     * @param namespace the namespaces
     * @return ResourceQuotaList the ResourceQuotaList
     */
    Map createNamespaces(Object namespace){
        return (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListNamespaceCreateUrl(), HttpMethod.POST, namespace, Map.class);
    }

}
