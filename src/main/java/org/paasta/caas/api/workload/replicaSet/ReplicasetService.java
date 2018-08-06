package org.paasta.caas.api.workload.replicaSet;

import org.paasta.caas.api.config.EnvConfig;
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

import java.util.HashMap;
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

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EnvConfig envConfig;



    /**
     * ReplicaSet List 조회(전체 네임스페이스 조회)
     *
     * @return Map
     */
    public Map<String, Object> getReplicaSetListByAllNamespace(Map<String, Object> map) {
        Map result = new HashMap();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+envConfig.getKubeAdminToken());
        headers.add("Content-Type", "application/json");

        Map<String, Object> param = new HashMap<>();
        HttpEntity<Map> resetEntity = new HttpEntity(param, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(envConfig.getKubeApiUrl() + "/apis/apps/v1/replicasets", HttpMethod.GET, resetEntity, Map.class);

        LOGGER.debug(responseEntity.getBody().toString());

        result.put("result", "success");
        result.put("msg", "You have successfully completed the task.");
        result.put("data", responseEntity.getBody());
        result.put("statusCode", responseEntity.getStatusCodeValue());

        return result;
    }

    /**
     * ReplicaSet List 조회
     *
     * @return Map
     */
    public Map<String, Object> getReplicaSetList(String namespace, Map<String, Object> map) {
        Map result = new HashMap();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+envConfig.getKubeAdminToken());
        headers.add("Content-Type", "application/json");

        Map<String, Object> param = new HashMap<>();
        HttpEntity<Map> resetEntity = new HttpEntity(param, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(envConfig.getKubeApiUrl() + "/apis/apps/v1/namespaces/"+namespace+"/replicasets", HttpMethod.GET, resetEntity, Map.class);

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
        headers.add("Authorization", "Bearer "+envConfig.getKubeAdminToken());
        headers.add("Content-Type", "application/json");

        Map<String, Object> param = new HashMap<>();
        HttpEntity<Map> resetEntity = new HttpEntity(param, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(envConfig.getKubeApiUrl() + "/apis/apps/v1/namespaces/"+namespace+"/replicasets/"+replicasetsName, HttpMethod.GET, resetEntity, Map.class);

        LOGGER.debug(responseEntity.getBody().toString());

        result.put("result", "success");
        result.put("msg", "You have successfully completed the task.");
        result.put("data", responseEntity.getBody());
        result.put("statusCode", responseEntity.getStatusCodeValue());

        return result;
    }

}
