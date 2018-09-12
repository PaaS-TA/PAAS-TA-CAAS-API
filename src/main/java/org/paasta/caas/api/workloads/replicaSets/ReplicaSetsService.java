package org.paasta.caas.api.workloads.replicaSets;

import org.paasta.caas.api.common.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * ReplicaSet Service 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.8.01
 */
@Service
public class ReplicaSetsService {

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
    public ReplicaSetsService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * ReplicaSet 목록을 조회한다.
     *
     * @param namespace the namespace
     * @return the replicaset list
     */
    public ReplicaSetsList getReplicasetList(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListReplicasetsListUrl()
                        .replace("{namespace}", namespace), HttpMethod.GET, null, Map.class);

        return (ReplicaSetsList) commonService.setResultModel(commonService.setResultObject(resultMap, ReplicaSetsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * ReplicaSet 상세 정보를 조회한다.
     *
     * @param namespace       the namespace
     * @param replicasetsName the replicasets name
     * @return the custom services
     */
    public ReplicaSets getReplicaset(String namespace, String replicasetsName) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListReplicasetsGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", replicasetsName)
                        , HttpMethod.GET, null, Map.class);

        return (ReplicaSets) commonService.setResultModel(commonService.setResultObject(resultMap, ReplicaSets.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * ReplicaSet YAML을 조회한다.
     *
     * @param namespace   the namespace
     * @param replicasetsName the ReplicaSets name
     * @return the custom ReplicaSets yaml
     */
    public ReplicaSets getReplicasetYaml(String namespace, String replicasetsName) {
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListReplicasetsGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", replicasetsName), HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML);

        //noinspection unchecked
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("sourceTypeYaml", resultString);

        return (ReplicaSets) commonService.setResultModel(commonService.setResultObject(resultMap, ReplicaSets.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * ReplicaSet 목록을 조회한다. (Label Selector)
     *
     * @param namespace the namespace
     * @param selectors the selectors
     * @return the custom services list
     */
    public ReplicaSetsList getReplicasetListLabelSelector(String namespace, String selectors) {
        String requestSelector = "?labelSelector=" + selectors;
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListReplicasetsListUrl()
                        .replace("{namespace}", namespace) + requestSelector, HttpMethod.GET, null, Map.class);

        return (ReplicaSetsList) commonService.setResultModel(commonService.setResultObject(resultMap, ReplicaSetsList.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
