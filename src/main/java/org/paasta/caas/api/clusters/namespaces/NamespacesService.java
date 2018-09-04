package org.paasta.caas.api.clusters.namespaces;

import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * CLUSTER Service
 *
 * @author indra
 * @version 1.0
 * @since 2018.8.01 최초작성
 */
@Service
public class NamespacesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NamespacesService.class);
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;

    /**
     * Instantiates a new Namespace service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     */
    @Autowired
    public NamespacesService(RestTemplateService restTemplateService, CommonService commonService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
    }


    /**
     * Gets namespaces.
     *
     * @return the namespaces
     */
    Namespaces getNamespaces(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_NAMESPACES+"/"+namespace, HttpMethod.GET, null, Map.class);

        return (Namespaces) commonService.setResultModel(commonService.setResultObject(resultMap, Namespaces.class), Constants.RESULT_STATUS_SUCCESS);
    }

    ResourceQuotaList getResourceQuotaList(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_NAMESPACES+"/"+namespace+"/resourcequotas", HttpMethod.GET, null, Map.class);

        return (ResourceQuotaList) commonService.setResultModel(commonService.setResultObject(resultMap, ResourceQuotaList.class), Constants.RESULT_STATUS_SUCCESS);
    }

}
