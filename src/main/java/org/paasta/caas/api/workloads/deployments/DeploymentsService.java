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
 * @author PHR
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
    public DeploymentsList getDeploymentsList(String namespace) {
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
     * @param deploymentName the deployments name
     * @return the deployments
     */
    public Deployments getDeployments(String namespace, String deploymentName) {
        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListDeploymentsGet()
                        .replace("{namespace}", namespace)
                        .replace("{name}", deploymentName)
                        , HttpMethod.GET, null, Map.class);

        return (Deployments) commonService.setResultModel(commonService.setResultObject(responseMap, Deployments.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Deployments YAML을 조회한다.
     *
     * @param namespace       the namespace
     * @param deploymentName the deployments name
     * @param resultMap       the result map
     * @return the deployments yaml
     */
    public Deployments getDeploymentsYaml(String namespace, String deploymentName, HashMap resultMap) {
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListDeploymentsGet()
                        .replace("{namespace}", namespace)
                        .replace("{name}", deploymentName), HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML);

        //noinspection unchecked
        resultMap.put("sourceTypeYaml", resultString);

        return (Deployments) commonService.setResultModel(commonService.setResultObject(resultMap, Deployments.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Deployments 를 생성한다.
     *
     * @param namespace       the namespace
     * @param deployments     the deployments
     * @param resultMap       the result map
     * @return the deployments yaml
     */
    public Map<?,?> createDeploymentsYaml(String namespace, Object deployments, HashMap resultMap) {
        System.out.println("namespace:::::" + namespace );
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListDeploymentsCreate()
                        .replace("{namespace}", namespace), HttpMethod.POST, deployments, String.class, Constants.ACCEPT_TYPE_YAML);
        resultMap.put("sourceTypeYaml", resultString);

        return resultMap;
    }

    /**
     * Deployments 를 삭제한다.
     *
     * @param namespace       the namespace
     * @param name             the deployments name
     * @param resultMap       the result map
     * @return the deployments yaml
     */
    public Map<?,?> deleteDeploymentsYaml(String namespace, String name, HashMap resultMap) {
        System.out.println("namespace:::::" + namespace );
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListDeploymentsDelete()
                        .replace("{namespace}", namespace).replace("{name}", name), HttpMethod.DELETE, null, String.class, Constants.ACCEPT_TYPE_YAML);
        resultMap.put("sourceTypeYaml", resultString);

        return resultMap;
    }

}
