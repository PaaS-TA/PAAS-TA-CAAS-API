package org.paasta.caas.api.apis.cluster;

import org.paasta.caas.api.config.EnvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import org.apache.http.client.HttpClient;
//import javax.servlet.http.HttpServletResponse;

import java.util.*;


/**
 * CLUSTER Service
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.8.01 최초작성
 */
@Service
public class ClusterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterService.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EnvConfig envConfig;

    /**
     * 네임스페이스 리스트 조회
     *
     * @return int int
     */
    public Map<String, Object> getNamespaceList() {
        Map result = new HashMap();
        //try {
        // ToBe 토큰 및 설정값 프로퍼티 참조
        // response : Map request : vo
        // 헤더 셋팅 부분 및 공통화 가능부분 리펙토링

        String tempToken = envConfig.getCaasUrl();
        LOGGER.info("tempToken:: "+tempToken);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+tempToken);
        headers.add("Content-Type", "application/json");

        Map<String, Object> param = new HashMap<>();
        param.put("reqParam_sample_1", "1");
        param.put("reqParam_sample_2", "2");

        HttpEntity<Map> resetEntity = new HttpEntity(param, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(envConfig.getCaasUrl() + "/api/v1/namespaces", HttpMethod.GET, resetEntity, Map.class);

        LOGGER.debug(responseEntity.getBody().toString());

        result.put("result", "success");
        result.put("msg", "You have successfully completed the task.");
        result.put("data", responseEntity.getBody());
        result.put("statusCode", responseEntity.getStatusCode());

        // }
//        // 해당 부분의 exception catch   GlobalControllerExceptionHandler 의 ExceptionHandler 보다 우선순위가 높기에 특정 처리 필요시 작성
//        catch (Exception e) {
//            e.printStackTrace();
//            result.put("result", "fail");
//            result.put("msg", e.getMessage());
//            result.put("statusCode", e.get);
//        }
        return result;
    }


}
