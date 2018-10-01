package org.paasta.caas.api.roles;

import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Role Service 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018 -08-14
 */
@Service
public class RolesService {

    private final RestTemplateService restTemplateService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Roles service
     *
     * @param restTemplateService the rest template service
     * @param propertyService     the property service
     */
    @Autowired
    public RolesService(RestTemplateService restTemplateService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.propertyService = propertyService;
    }


    /**
     * Role 을 삭제한다.
     *
     * @param namespace the namespace
     * @param roleName  the roleName
     * @return String string
     */
    String deleteRole(String namespace, String roleName) {
        String apiUrl = propertyService.getCaasMasterApiListRolesDeleteUrl()
                .replace("{namespace}", namespace)
                .replace("{name}", roleName);

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.DELETE, null, Map.class);

        if (responseMap != null) {
            return Constants.RESULT_STATUS_SUCCESS;
        }
        return Constants.RESULT_STATUS_FAIL;
    }

    /**
     * Role 을 수정한다.
     *
     * @param namespace the namespace
     * @param roleName  the roleName
     * @param yml       the yml
     * @return String string
     */
    String updateRole(String namespace, String roleName, String yml) {
        String apiUrl = propertyService.getCaasMasterApiListRolesUpdateUrl()
                .replace("{namespace}", namespace)
                .replace("{name}", roleName);

        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.PUT, yml, String.class, Constants.ACCEPT_TYPE_YAML, Constants.ACCEPT_TYPE_YAML);

        if (resultString != null) {
            return Constants.RESULT_STATUS_SUCCESS;
        }
        return Constants.RESULT_STATUS_FAIL;
    }
}
