package org.paasta.caas.api.cluster.namespaces;

import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.RestTemplateService;
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
public class NamespaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NamespaceService.class);
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;

//    TODO :: REMOVE
    @Autowired
    RestTemplate restTemplate;

//    TODO :: REMOVE
    @Autowired
    private EnvConfig envConfig;

    @Autowired
    public NamespaceService(RestTemplateService restTemplateService, CommonService commonService) {this.restTemplateService = restTemplateService;
        this.commonService = commonService;
    }

    // TODO :: REMOVE
    public Map<String, Object> getNamespaceList(Map<String, Object> map) {
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
            kubeAccountToken = envConfig.getKubeAdminToken();
            LOGGER.info("Get AdminToken : "+ kubeAccountToken);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+kubeAccountToken);
        headers.add("Content-Type", "application/json");

        Map<String, Object> param = new HashMap<>();
        param.put("reqParam_sample_1", "1");
        param.put("reqParam_sample_2", "2");

        HttpEntity<Map> resetEntity = new HttpEntity(param, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(envConfig.getKubeApiUrl() + "/api/v1/namespaces", HttpMethod.GET, resetEntity, Map.class);

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


    Namespace getNamespaceList() {
        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_NAMESPACES_LIST, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getNamespaceList() :: hashMap.toString() :: {}", hashMap.toString());

        Namespace result = new Namespace();
        result.setResult(Constants.RESULT_STATUS_SUCCESS);
        result.setItems(commonService.setListData(Namespace.class, "metadata", (List) hashMap.get("items")));
//        result.setItems(setListData(new Namespace(), "metadata", (List) hashMap.get("items"))); // SAME RESULT

        return result;
    }



}
