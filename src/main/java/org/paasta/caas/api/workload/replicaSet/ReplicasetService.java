package org.paasta.caas.api.workload.replicaSet;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.kubernetes.client.models.V1beta1ReplicaSetList;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.http.client.HttpClient;
//import javax.servlet.http.HttpServletResponse;


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
    private final CommonService commonService;

    @Autowired
    public ReplicasetService(RestTemplateService restTemplateService, CommonService commonService) {this.restTemplateService = restTemplateService;
        this.commonService = commonService;
    }

    /**
     * ReplicaSet List 조회(전체 네임스페이스 조회)
     *
     * @return Map
     */
    ReplicasetList getReplicaSetListByAllNamespace() {
        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_REPLICASET_LIST, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getNamespaceList() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        ReplicasetList replicasetList = gson.fromJson(gson.toJson(hashMap), ReplicasetList.class);

//        Namespace result = new Namespace();
//        result.setResult(Constants.RESULT_STATUS_SUCCESS);
//        result.setItems(commonService.setListData(Namespace.class, "metadata", (List) hashMap.get("items")));
//        result.setItems(setListData(new Namespace(), "metadata", (List) hashMap.get("items"))); // SAME RESULT

        return replicasetList;
    }



    /**
     * ReplicaSet List 조회
     *
     * @return Map
     */
    public Map<String, Object> getReplicaSetList(String namespace, Map<String, Object> map) {
        Map result = new HashMap();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+"");
        headers.add("Content-Type", "application/json");

        Map<String, Object> param = new HashMap<>();
        HttpEntity<Map> resetEntity = new HttpEntity(param, headers);
        ResponseEntity<Map> responseEntity = null;//restTemplate.exchange("" + "/apis/apps/v1/namespaces/"+namespace+"/replicasets", HttpMethod.GET, resetEntity, Map.class);

        LOGGER.debug(responseEntity.getBody().toString());

        result.put("result", "success");
        result.put("msg", "You have successfully completed the task.");
        result.put("data", responseEntity.getBody());
        result.put("statusCode", responseEntity.getStatusCodeValue());

        return result;
    }

    /**
     * ReplicaSet 상세 조회
     *
     * @return Map
     */
    public Map<String, Object> getReplicaSet(String namespace, String replicasetsName, Map<String, Object> map) {
        Map result = new HashMap();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+"");
        headers.add("Content-Type", "application/json");

        Map<String, Object> param = new HashMap<>();
        HttpEntity<Map> resetEntity = new HttpEntity(param, headers);
        ResponseEntity<Map> responseEntity = null; //restTemplate.exchange("" + "/apis/apps/v1/namespaces/"+namespace+"/replicasets/"+replicasetsName, HttpMethod.GET, resetEntity, Map.class);

        LOGGER.debug(responseEntity.getBody().toString());

        result.put("result", "success");
        result.put("msg", "You have successfully completed the task.");
        result.put("data", responseEntity.getBody());
        result.put("statusCode", responseEntity.getStatusCodeValue());

        return result;
    }

}
