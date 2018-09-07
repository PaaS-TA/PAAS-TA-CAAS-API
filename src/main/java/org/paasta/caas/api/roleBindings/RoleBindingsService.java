package org.paasta.caas.api.roleBindings;

import org.paasta.caas.api.common.CommonService;
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
    private final CommonService commonService;

    @Autowired
    public RoleBindingsService(RestTemplateService restTemplateService, CommonService commonService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
    }

    @Autowired
    PropertyService propertyService;


    /**
     * RoleBindingList 조회 (특정 네임스페이스에서 조회)
     *
     * @return Map
     */
    public RoleBindingsList getRoleBindingList(String namespace) {
        RoleBindingsList roleBindingsList;
        String resultCode;

        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsListUrl().replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleBindingList() :: responseMap.toString() :: {}", responseMap.toString());

        roleBindingsList = commonService.setResultObject(responseMap, RoleBindingsList.class);
        resultCode = Constants.RESULT_STATUS_SUCCESS;

        return (RoleBindingsList) commonService.setResultModel(roleBindingsList, resultCode);
    }

    /**
     * RoleBinding 객체를 조회한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    public RoleBindings getRoleBinding(String namespace, String roleBindingName) {
        RoleBindings roleBindings;
        String resultCode;

        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsGetUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleBindingName);

        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRole() :: responseMap.toString() :: {}", responseMap.toString());

        roleBindings = commonService.setResultObject(responseMap, RoleBindings.class);
        resultCode = Constants.RESULT_STATUS_SUCCESS;

        return (RoleBindings) commonService.setResultModel(roleBindings, resultCode);
    }


    /**
     * RoleBinding 권한을 할당한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBinding
     * @return
     */
    public RoleBindings createRoleBinding(String namespace, RoleBindings roleBinding) {
        RoleBindings roleBindings;
        String resultCode;

        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsCreateUrl().replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.POST, roleBinding, Map.class);
        LOGGER.info("########## createRoleBinding() :: responseMap.toString() :: {}", responseMap.toString());

        roleBindings = commonService.setResultObject(responseMap, RoleBindings.class);
        resultCode = Constants.RESULT_STATUS_SUCCESS;

        return (RoleBindings) commonService.setResultModel(roleBindings, resultCode);
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

        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.DELETE, null, Map.class);
        LOGGER.info("########## deleteRoleBinding() :: responseMap.toString() :: {}", responseMap.toString());

        if(responseMap.get("status").equals("Success")){
            return Constants.RESULT_STATUS_SUCCESS;
        }
        return Constants.RESULT_STATUS_FAIL;
    }

    /**
     * RoleBinding 권한을 수정한다.
     *
     * @param namespace
     * @param roleBindingName
     * @param yml
     * @return
     */
    public String updateRoleBinding(String namespace, String roleBindingName, String yml) {
        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.APIS_URL_NAMESPACES + "/" + namespace + "/rolebindings/" + roleBindingName, HttpMethod.PUT, yml, Map.class, "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");

        LOGGER.info("########## updateRoleBinding() :: responseMap.toString() :: {}", responseMap.toString());

        if(responseMap != null){
            return Constants.RESULT_STATUS_SUCCESS;
        }
        return Constants.RESULT_STATUS_FAIL;
    }
}
