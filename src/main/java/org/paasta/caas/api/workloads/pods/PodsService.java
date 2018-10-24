package org.paasta.caas.api.workloads.pods;

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
 * Pods Service 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.01
 */
@Service
public class PodsService {
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Pods service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public PodsService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * Pod 목록을 조회한다.
     *
     * @param namespace the namespace
     * @return the pod list
     */
    PodsList getPodList(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPodsListUrl().replace("{namespace}", namespace), HttpMethod.GET, null, Map.class);

        return (PodsList) commonService.setResultModel(commonService.setResultObject(resultMap, PodsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Selector를 이용해 Pod 목록을 조회한다.
     *
     * @param namespace the namespace
     * @param selector  the selector
     * @return the pod list
     */
    PodsList getPodListWithLabelSelector(String namespace, String selector) {
        String requestSelector = "?labelSelector=" + selector;
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPodsListUrl().replace("{namespace}", namespace) + requestSelector, HttpMethod.GET, null, Map.class);

        return (PodsList) commonService.setResultModel(commonService.setResultObject(resultMap, PodsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Node 이름을 이용해 Pod 목록을 조회한다.
     *
     * @param namespace               the namespace
     * @param nodeName                the node name
     * @return the pod list
     */
    PodsList getPodListByNode(String namespace, String nodeName) {
        String requestURL = propertyService.getCaasMasterApiListPodsListUrl().replace("{namespace}", namespace)
                + "/?fieldSelector=spec.nodeName=" + nodeName;

        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, requestURL,
                HttpMethod.GET, null, Map.class);
        return (PodsList) commonService.setResultModel(commonService.setResultObject(resultMap, PodsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Pod를 조회한다.
     *
     * @param namespace the namespace
     * @param podName   the pod's name
     * @return the pod
     */
    Pods getPod(String namespace, String podName) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPodsGetUrl().replace("{namespace}", namespace).replace("{name}", podName),
                HttpMethod.GET, null, Map.class);

        return (Pods) commonService.setResultModel(commonService.setResultObject(resultMap, Pods.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Pod의 YAML을 조회한다.
     *
     * @param namespace the namespace
     * @param podName   the pod's name
     * @return the pods
     */
    Pods getPodYaml(String namespace, String podName) {
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPodsGetUrl().replace("{namespace}", namespace).replace("{name}", podName),
                HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML);
        //noinspection unchecked
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("sourceTypeYaml", resultString);

        return (Pods) commonService.setResultModel(commonService.setResultObject(resultMap, Pods.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
