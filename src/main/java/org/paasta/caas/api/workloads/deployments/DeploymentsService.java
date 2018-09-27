package org.paasta.caas.api.workloads.deployments;

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
 * Deployments Service 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Service
public class DeploymentsService {
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new deployments service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public DeploymentsService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * Deployments 목록을 조회한다.
     *
     * @param namespace the namespace
     * @return the deployments list
     */
    public DeploymentsList getDeploymentList(String namespace) {
        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListDeploymentsList()
                        .replace("{namespace}", namespace)
                        , HttpMethod.GET, null, Map.class);

        return (DeploymentsList) commonService.setResultModel(commonService.setResultObject(responseMap, DeploymentsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Deployments 상세 정보를 조회한다.
     *
     * @param namespace       the namespace
     * @param deploymentsName the deployments name
     * @return the deployments
     */
    public Deployments getDeployment(String namespace, String deploymentsName) {
        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListDeploymentsGet()
                        .replace("{namespace}", namespace)
                        .replace("{name}", deploymentsName)
                        , HttpMethod.GET, null, Map.class);

        return (Deployments) commonService.setResultModel(commonService.setResultObject(responseMap, Deployments.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Deployments YAML을 조회한다.
     *
     * @param namespace       the namespace
     * @param deploymentsName the deployments name
     * @param resultMap       the result map
     * @return the deployments yaml
     */
    public Deployments getDeploymentsYaml(String namespace, String deploymentsName, HashMap resultMap) {
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListDeploymentsGet()
                        .replace("{namespace}", namespace)
                        .replace("{name}", deploymentsName), HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML);

        //noinspection unchecked
        resultMap.put("sourceTypeYaml", resultString);

        return (Deployments) commonService.setResultModel(commonService.setResultObject(resultMap, Deployments.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Deployments 목록을 조회한다. (Label Selector)
     *
     * @param namespace the namespace
     * @param selector  the selector
     * @return the deployments list
     */
    public DeploymentsList getDeploymentsListLabelSelector(String namespace, String selector) {
        String requestSelector = "?labelSelector=" + selector;
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListDeploymentsList()
                        .replace("{namespace}", namespace) + requestSelector, HttpMethod.GET, null, Map.class);

        return (DeploymentsList) commonService.setResultModel(commonService.setResultObject(resultMap, DeploymentsList.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
