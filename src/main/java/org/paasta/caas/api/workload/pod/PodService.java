package org.paasta.caas.api.workload.pod;

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
public class PodService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PodService.class);

    @Autowired
    RestTemplate restTemplate;

    /**
     * 네임스페이스 리스트 조회
     *
     * @return int int
     */
    public Map<String, Object> getPodList(String namespace, Map<String, Object> map) {
        Map result = new HashMap();
        //try {
        // ToBe
        // response : Map request : vo
        // 헤더 셋팅 부분 및 공통화 가능부분 리펙토링

        String kubeAccountToken;
        if (!ObjectUtils.isEmpty(map.get("token"))) {
            kubeAccountToken = map.get("token").toString();
            LOGGER.info("Get InputToken : "+ kubeAccountToken);
        }else{
            kubeAccountToken = "";
            LOGGER.info("Get AdminToken : "+ kubeAccountToken);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+kubeAccountToken);
        headers.add("Content-Type", "application/json");

        Map<String, Object> param = new HashMap<>();
        param.put("reqParam_sample_1", "1");
        param.put("reqParam_sample_2", "2");

        HttpEntity<Map> resetEntity = new HttpEntity(param, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange("" + "/api/v1/namespaces/"+namespace+"/pods", HttpMethod.GET, resetEntity, Map.class);

        LOGGER.debug(responseEntity.getBody().toString());

        result.put("result", "success");
        result.put("msg", "You have successfully completed the task.");
        result.put("data", responseEntity.getBody());
        result.put("statusCode", responseEntity.getStatusCodeValue());

        // restTemplate 에 대한 예외처리는 GlobalControllerExceptionHandler 에서 처리
        // 서비스단에서 처리하는 exception 처리는
        // GlobalControllerExceptionHandler 의 ExceptionHandler 보다 우선순위가 높기에 특정 처리 필요시에만 작성
        // }
//        //
//        catch (Exception e) {
//            e.printStackTrace();
//            result.put("result", "fail");
//            result.put("msg", e.getMessage());
//            result.put("statusCode", e.get);
//        }
        return result;
    }



}
