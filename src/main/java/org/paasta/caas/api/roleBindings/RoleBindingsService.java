package org.paasta.caas.api.roleBindings;

import com.google.gson.Gson;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.paasta.caas.api.roles.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * RoleBinding Service class
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-17
 */
@Service
public class RoleBindingsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolesService.class);
    private final RestTemplateService restTemplateService;

    @Autowired
    public RoleBindingsService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @Autowired
    PropertyService propertyService;


    /**
     * RoleBindingList 조회 (모든 네임스페이스에서 조회)
     *
     * @return Map
     */
    public RoleBindingsList getRoleBindingListByAllNamespace() {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsAllListUrl();

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleBindingListByAllNamespaces() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleBindingsList.class);
    }

    /**
     * RoleBindingList 조회 (특정 네임스페이스에서 조회)
     *
     * @return Map
     */
    public RoleBindingsList getRoleBindingList(String namespace) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsListUrl().replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleBindingList() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleBindingsList.class);
    }

    /**
     * RoleBinding 객체를 조회한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    public RoleBindings getRoleBinding(String namespace, String roleBindingName) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsGetUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleBindingName);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRole() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleBindings.class);
    }


    /**
     * RoleBinding 권한을 할당한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBinding
     * @return
     */
    public RoleBindings createRoleBinding(String namespace, RoleBindings roleBinding) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsCreateUrl().replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.POST, roleBinding, Map.class);
        LOGGER.info("########## createRoleBinding() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleBindings.class);
    }

    /**
     * RoleBinding 권한을 해지한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    public String deleteRoleBinding(String namespace, String roleBindingName) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsDeleteUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleBindingName);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.DELETE, null, Map.class);
        LOGGER.info("########## deleteRoleBinding() :: hashMap.toString() :: {}", hashMap.toString());

        if(hashMap.get("status").equals("Success")){
            return Constants.RESULT_STATUS_SUCCESS;
        }
        return Constants.RESULT_STATUS_FAIL;
    }
}
