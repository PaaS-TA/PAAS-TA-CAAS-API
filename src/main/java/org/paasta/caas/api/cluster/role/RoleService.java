package org.paasta.caas.api.cluster.role;

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
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@Service
public class RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
    private final RestTemplateService restTemplateService;

    @Autowired
    public RoleService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @Autowired
    PropertyService propertyService;


    /**
     * RoleList 조회 (모든 네임스페이스에서 조회)
     *
     * @return Map
     */
    public RoleList getRoleListByAllNamespace() {
        String apiUrl = propertyService.getCaasMasterApiListRoleAllListUrl();

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleListByAllNamespaces() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleList.class);
    }

    /**
     * RoleList 조회 (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @return Map
     */
    public RoleList getRoleList(String namespace) {
        String apiUrl = propertyService.getCaasMasterApiListRoleListUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleList() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleList.class);
    }


    /**
     * Role 상세 조회 (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleName
     * @return Map
     */
    public Role getRole(String namespace, String roleName) {
        String apiUrl = propertyService.getCaasMasterApiListRoleGetUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleName);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRole() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), Role.class);
    }
}
