package org.paasta.caas.api.authenticate;

import org.paasta.caas.api.clusters.namespaces.ResourceQuotaList;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * DashBoard 에서 인증을 위해 호출 받는 서비스.
 *
 * @author indra
 * @version 1.0
 * @since 2018.09.05 최초작성
 */
@Service
public class AuthenticateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateService.class);

    private final RestTemplateService restTemplateService;
    private final CommonService commonService;

    @Autowired
    public AuthenticateService(RestTemplateService restTemplateService, CommonService commonService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
    }

    /**
     * createUser 유저를 생성.
     *
     * @param namespace
     * @param yml
     * @return
     */
    public void createUser(String namespace, String yml) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_NAMESPACES+"/"+namespace+"/serviceaccounts", HttpMethod.POST, yml, Map.class, "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");
    }

    /**
     * createRole 롤을 생성.
     *
     * @param namespace
     * @param yml
     * @return
     */
    public void createRole(String namespace, String yml) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.APIS_URL_NAMESPACES+"/"+namespace+"/roles", HttpMethod.POST, yml, Map.class, "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");
    }

    /**
     * createRoleBinding 롤을 바인딩.
     *
     * @param namespace
     * @param yml
     * @return
     */
    public void createRoleBinding(String namespace, String yml) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.APIS_URL_NAMESPACES+"/"+namespace+"/rolebindings", HttpMethod.POST, yml, Map.class, "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");
    }

    /**
     * getToken 토큰을 조회.
     *
     * @param namespace
     * @param userName
     * @return the String
     */
    public String getToken(String namespace, String userName) {
        return restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_NAMESPACES+"/"+namespace+"/serviceaccounts/"+userName, HttpMethod.GET, null, String.class, "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");
    }
}
