package org.paasta.caas.api.workload.replicaSet;

import com.google.gson.Gson;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    private final RestTemplateService restTemplateService;

    @Autowired
    public ReplicasetService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @Autowired
    PropertyService propertyService;

    /**
     * ReplicaSet List 조회(전체 네임스페이스 조회)
     *
     * @return Map
     */
    ReplicasetList getReplicaSetListByAllNamespace() {
        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_REPLICASET_LIST, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getNamespaceList() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), ReplicasetList.class);
    }


    /**
     * ReplicaSet List 조회
     *
     * @return Map
     */
    public ReplicasetList getReplicaSetList(String namespace, Map<String, Object> map) {

        String apiUrl = propertyService.getCaasMasterApiListReplicasetListUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap hashMap = (HashMap) restTemplateService.send(propertyService.getCaasMasterApiUrl(), apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getReplicaSetList() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), ReplicasetList.class);
    }

    /**
     * ReplicaSet 상세 조회
     *
     * @return Map
     */
    public Replicaset getReplicaSet(String namespace, String replicasetsName, Map<String, Object> map) {
        String apiUrl = propertyService.getCaasMasterApiListReplicasetGetUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", replicasetsName);

        HashMap hashMap = (HashMap) restTemplateService.send(propertyService.getCaasMasterApiUrl(), apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getReplicaSet() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), Replicaset.class);
    }

}
