package org.paasta.caas.api.roles;

import com.google.gson.Gson;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Role Service Class
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@Service
public class RolesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolesService.class);
    private final RestTemplateService restTemplateService;

    @Autowired
    public RolesService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @Autowired
    PropertyService propertyService;


    /**
     * RoleList 조회 (모든 네임스페이스에서 조회)
     *
     * @return Map
     */
    public RolesList getRoleListByAllNamespace() {
        String apiUrl = propertyService.getCaasMasterApiListRolesAllListUrl();

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleListByAllNamespaces() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RolesList.class);
    }

    /**
     * RoleList 조회 (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @return Map
     */
    public RolesList getRoleList(String namespace) {
        String apiUrl = propertyService.getCaasMasterApiListRolesListUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleList() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RolesList.class);
    }


    /**
     * Role 상세 조회 (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleName
     * @return Map
     */
    public Roles getRole(String namespace, String roleName) {
        String apiUrl = propertyService.getCaasMasterApiListRolesGetUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleName);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRole() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), Roles.class);
    }

    /**
     * Role 을 삭제한다.
     *
     * @param namespace
     * @param roleName
     * @return
     */
    public String deleteRole(String namespace, String roleName) {
        String apiUrl = propertyService.getCaasMasterApiListRolesDeleteUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleName);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.DELETE, null, Map.class);
        LOGGER.info("########## deleteRole() :: hashMap.toString() :: {}", hashMap.toString());

        if(hashMap != null){
            return Constants.RESULT_STATUS_SUCCESS;
        }
        return Constants.RESULT_STATUS_FAIL;
    }

}
