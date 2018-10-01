package org.paasta.caas.api.authenticate;

import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Authenticate Service 클래스.
 *
 * @author indra
 * @version 1.0
 * @since 2018.09.05 최초작성
 */
@Service
public class AuthenticateService {

    private final RestTemplateService restTemplateService;
    private final PropertyService propertyService;

    /**
     * Instantiates a Authenticate service.
     *
     * @param restTemplateService the rest template service
     * @param propertyService     the property service
     */
    @Autowired
    public AuthenticateService(RestTemplateService restTemplateService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.propertyService = propertyService;
    }

    /**
     * 유저를 생성한다.
     *
     * @param namespace the namespace
     * @param yml the yml
     */
    void createUser(String namespace, String yml) {
        restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListUsersCreateUrl()
                        .replace("{namespace}", namespace), HttpMethod.POST, yml, Map.class, "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");
    }

    /**
     * 롤을 생성한다.
     *
     * @param namespace the namespace
     * @param yml the yml
     */
    void createRole(String namespace, String yml) {
        restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListRolesCreateUrl()
                        .replace("{namespace}", namespace), HttpMethod.POST, yml, Map.class, "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");
    }

    /**
     * 롤을 바인딩한다.
     *
     * @param namespace the namespace
     * @param yml the yml
     */
    void createRoleBinding(String namespace, String yml) {
        restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListRoleBindingsCreateUrl()
                        .replace("{namespace}", namespace), HttpMethod.POST, yml, Map.class, "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");
    }

    /**
     * 토큰을 조회한다.
     *
     * @param namespace the namespace
     * @param userName the user name
     * @return the String
     */
    String getToken(String namespace, String userName) {
        return restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListUsersGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", userName), HttpMethod.GET, null, String.class, "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");
    }
}
