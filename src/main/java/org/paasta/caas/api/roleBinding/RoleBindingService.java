package org.paasta.caas.api.roleBinding;

import com.google.gson.Gson;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.paasta.caas.api.role.RoleService;
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
public class RoleBindingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
    private final RestTemplateService restTemplateService;

    @Autowired
    public RoleBindingService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @Autowired
    PropertyService propertyService;


    /**
     * RoleBindingList 조회 (모든 네임스페이스에서 조회)
     *
     * @return Map
     */
    public RoleBindingList getRoleBindingListByAllNamespace() {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingAllListUrl();

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleBindingListByAllNamespaces() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleBindingList.class);
    }

    /**
     * RoleBindingList 조회 (특정 네임스페이스에서 조회)
     *
     * @return Map
     */
    public RoleBindingList getRoleBindingList(String namespace) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingListUrl().replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleBindingList() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleBindingList.class);
    }

    /**
     * RoleBinding 객체를 조회한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    public RoleBinding getRoleBinding(String namespace, String roleBindingName) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingGetUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleBindingName);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRole() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleBinding.class);
    }


    /**
     * RoleBinding 권한을 할당한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBinding
     * @return
     */
    public RoleBinding createRoleBinding(String namespace, RoleBinding roleBinding) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingCreateUrl().replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.POST, roleBinding, Map.class);
        LOGGER.info("########## createRoleBinding() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleBinding.class);
    }

    /**
     * RoleBinding 권한을 해지한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    public String deleteRoleBinding(String namespace, String roleBindingName) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingDeleteUrl()
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
