package org.paasta.caas.api.workloads.replicaSets;

import org.paasta.caas.api.common.*;
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
 * @author CISS
 * @version 1.0
 * @since 2018.8.01 최초작성
 */
@Service
public class ReplicaSetsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplicaSetsService.class);
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
    public ReplicaSetsService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

/*
    //ReplicaSet List 조회(전체 네임스페이스 조회)
    ReplicaSetsList getReplicaSetListByAllNamespace() {
        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_REPLICASET_LIST, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getNamespaceList() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), ReplicaSetsList.class);
    }
*/

    /**
     * ReplicaSet List 조회
     *
     * @return Map
     */
    public ReplicaSetsList getReplicasetList(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListReplicasetsListUrl()
                        .replace("{namespace}", namespace), HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (ReplicaSetsList) commonService.setResultModel(commonService.setResultObject(resultMap, ReplicaSetsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * ReplicaSet 상세 조회
     *
     * @return Map
     */
    public ReplicaSets getReplicaset(String namespace, String replicasetsName) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListReplicasetsGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", replicasetsName)
                        , HttpMethod.GET, null, Map.class);

        resultMap.put("source",new LinkedHashMap(resultMap));

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        // TODO : 삭제 및 업데이트 예정 CISS 2018.08.31
        // 아래 GsonBuilder 는 Gson내에서 map 처리시 숫자형(integer)가 double(소수점)형으로 출력되는 부분을 수정 처리하는 테스트 중입니다.
//        Gson gson = new GsonBuilder()
//                //.registerTypeAdapter(Integer.class, new IntegerSerializer())
//                .registerTypeAdapter(Double.class, new GsonDeserializer())
//                .registerTypeAdapter(Integer.class, new GsonDeserializer())
//                .registerTypeAdapter(BigDecimal.class, new GsonDeserializer())
//                .registerTypeAdapter(Number.class, new GsonDeserializer())
//                .create();

        return (ReplicaSets) commonService.setResultModel(commonService.setResultObject(resultMap, ReplicaSets.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * ReplicaSet List Label 조회
     *
     * @return Map
     */
    public ReplicaSetsList getReplicasetListLabelSelector(String namespace, String selectors) {
        String requestSelector = "?labelSelector=" + selectors;
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListReplicasetsListUrl()
                        .replace("{namespace}", namespace) + requestSelector, HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (ReplicaSetsList) commonService.setResultModel(commonService.setResultObject(resultMap, ReplicaSetsList.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
