package org.paasta.caas.api.workload.replicaset;

import com.google.gson.Gson;
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
 * CLUSTER Service
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.8.01 최초작성
 */
@Service
public class ReplicasetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplicasetService.class);
    private final CommonService commonService;
    private final PropertyService propertyService;
    private final RestTemplateService restTemplateService;

    /**
     * Instantiates a new Custom service service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public ReplicasetService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

/*
    //ReplicaSet List 조회(전체 네임스페이스 조회)
    ReplicasetList getReplicaSetListByAllNamespace() {
        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_REPLICASET_LIST, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getNamespaceList() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), ReplicasetList.class);
    }
*/

    /**
     * ReplicaSet List 조회
     *
     * @return Map
     */
    ReplicasetList getReplicasetList(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListReplicasetsListUrl().replaceAll("\\{" + "namespace" + "\\}", namespace), HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (ReplicasetList) commonService.setResultModel(new Gson().fromJson(new Gson().toJson(resultMap), ReplicasetList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * ReplicaSet 상세 조회
     *
     * @return Map
     */
    Replicaset getReplicaset(String namespace, String replicasetsName) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListReplicasetsGetUrl()
                        .replaceAll("\\{" + "namespace" + "\\}", namespace)
                        .replaceAll("\\{" + "name" + "\\}", replicasetsName), HttpMethod.GET, null, Map.class);

        resultMap.put("source",new LinkedHashMap(resultMap));

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (Replicaset) commonService.setResultModel(new Gson().fromJson(new Gson().toJson(resultMap), Replicaset.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * ReplicaSet List 조회
     *
     * @return Map
     */
    ReplicasetList getReplicasetListLabelSelector(String namespace, String selectors) {
        String requestSelector = "?labelSelector=" + selectors;
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListReplicasetsListUrl().replaceAll("\\{" + "namespace" + "\\}", namespace) + requestSelector, HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (ReplicasetList) commonService.setResultModel(new Gson().fromJson(new Gson().toJson(resultMap), ReplicasetList.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
