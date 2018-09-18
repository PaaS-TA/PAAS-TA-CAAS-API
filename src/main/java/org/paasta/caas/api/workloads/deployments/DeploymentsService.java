package org.paasta.caas.api.workloads.deployments;

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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Deployments 관련 API에 대해 CaaS API를 향해 호출하고, 결과값을 처리하는 서비스이다.
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
    public DeploymentsService(RestTemplateService restTemplateService, CommonService commonService,
                              PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * Deployments List 조회 (전체 네임스페이스 대상)
     *
     * @return the deployment list
     */
    public DeploymentsList getDeploymentListByAllNamespace() {
        final String requestPath = propertyService.getCaasMasterApiListDeploymentAllList();
        HashMap responseMap = (HashMap) restTemplateService.send(
                Constants.TARGET_CAAS_MASTER_API, requestPath, HttpMethod.GET, null, Map.class);

        return (DeploymentsList) commonService.setResultModel(commonService.setResultObject(responseMap, DeploymentsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Deployments List 조회 (특정 네임스페이스 대상)
     *
     * @param namespace the namespace
     * @return the deployment list
     */
    public DeploymentsList getDeploymentList(String namespace) {
        String requestPath = propertyService.getCaasMasterApiListDeploymentList().replace("{namespace}", namespace);
        HashMap responseMap = (HashMap) restTemplateService.send(
                Constants.TARGET_CAAS_MASTER_API, requestPath, HttpMethod.GET, null, Map.class);

        return (DeploymentsList) commonService.setResultModel(commonService.setResultObject(responseMap, DeploymentsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Deployments 조회 (특정 네임스페이스에 있는 deployment)
     *
     * @param namespace      the namespace
     * @param deploymentName the deployment name
     * @return the deployment
     */
    public Deployments getDeployment(String namespace, String deploymentName) {
        String requestPath = propertyService.getCaasMasterApiListDeploymentGet()
                .replace("{namespace}", namespace).replace("{deploymentName}", deploymentName);
        HashMap responseMap = (HashMap) restTemplateService.send(
                Constants.TARGET_CAAS_MASTER_API, requestPath, HttpMethod.GET, null, Map.class);
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                requestPath, HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML);

        //noinspection unchecked
        responseMap.put("sourceTypeYaml", resultString);
        //noinspection unchecked
        responseMap.put("source", new LinkedHashMap<String, Object>());

        return (Deployments) commonService.setResultModel(commonService.setResultObject(responseMap, Deployments.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Deployment List 조회 (selector를 이용)
     *
     * @param namespace the namespace
     * @param selector  the selector
     * @return the deployment list
     */
    public DeploymentsList getDeploymentsListLabelSelector(String namespace, String selector) {
        String requestSelector = "?labelSelector=" + selector;
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListDeploymentList()
                        .replace("{namespace}", namespace) + requestSelector, HttpMethod.GET, null, Map.class);

        return (DeploymentsList) commonService.setResultModel(commonService.setResultObject(resultMap, DeploymentsList.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
