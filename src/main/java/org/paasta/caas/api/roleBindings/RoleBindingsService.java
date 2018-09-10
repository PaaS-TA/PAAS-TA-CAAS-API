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
    private final PropertyService propertyService;

    @Autowired
    public RoleBindingsService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }


    /**
     * RoleBindingList 조회 (특정 네임스페이스에서 조회)
     *
     * @return Map
     */
    RoleBindingsList getRoleBindingList(String namespace) {
        /*TODO :: CHECK AND MODIFY*/
//        RoleBindingsList roleBindingsList;
//        String resultCode;

        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsListUrl().replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
//        LOGGER.info("########## getRoleBindingList() :: responseMap.toString() :: {}", responseMap.toString());

//        roleBindingsList = commonService.setResultObject(responseMap, RoleBindingsList.class);
//        resultCode = Constants.RESULT_STATUS_SUCCESS;

//        return (RoleBindingsList) commonService.setResultModel(roleBindingsList, resultCode);
        return (RoleBindingsList) commonService.setResultModel(commonService.setResultObject(responseMap, RoleBindingsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * RoleBinding 객체를 조회한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    RoleBindings getRoleBinding(String namespace, String roleBindingName) {
        /*TODO :: CHECK AND MODIFY*/
//        RoleBindings roleBindings;
//        String resultCode;

        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsGetUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleBindingName);

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
//        LOGGER.info("########## getRole() :: responseMap.toString() :: {}", responseMap.toString());

//        roleBindings = commonService.setResultObject(responseMap, RoleBindings.class);
//        resultCode = Constants.RESULT_STATUS_SUCCESS;

//        return (RoleBindings) commonService.setResultModel(roleBindings, resultCode);
        return (RoleBindings) commonService.setResultModel(commonService.setResultObject(responseMap, RoleBindings.class), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * RoleBinding 권한을 할당한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBinding
     * @return
     */
    RoleBindings createRoleBinding(String namespace, RoleBindings roleBinding) {
        /*TODO :: CHECK AND MODIFY*/
//        RoleBindings roleBindings;
//        String resultCode;

        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsCreateUrl().replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.POST, roleBinding, Map.class);
//        LOGGER.info("########## createRoleBinding() :: responseMap.toString() :: {}", responseMap.toString());

//        roleBindings = commonService.setResultObject(responseMap, RoleBindings.class);
//        resultCode = Constants.RESULT_STATUS_SUCCESS;

//        return (RoleBindings) commonService.setResultModel(roleBindings, resultCode);
        return (RoleBindings) commonService.setResultModel(commonService.setResultObject(responseMap, RoleBindings.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * RoleBinding 권한을 해지한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    String deleteRoleBinding(String namespace, String roleBindingName) {
        /*TODO :: CHECK AND MODIFY*/
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsDeleteUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleBindingName);

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.DELETE, null, Map.class);
//        LOGGER.info("########## deleteRoleBinding() :: responseMap.toString() :: {}", responseMap.toString());

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
    String updateRoleBinding(String namespace, String roleBindingName, String yml) {
        /*TODO :: CHECK AND MODIFY*/
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsUpdateUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleBindingName);

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.PUT, yml, Map.class,
                "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");

//        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
//                Constants.APIS_URL_NAMESPACES + "/" + namespace + "/rolebindings/" + roleBindingName,
//                HttpMethod.PUT, yml, Map.class,
//                "application/json,application/yaml,text/html", "application/yaml;charset=UTF-8");

//        LOGGER.info("########## updateRoleBinding() :: responseMap.toString() :: {}", responseMap.toString());

        if(responseMap != null){
            return Constants.RESULT_STATUS_SUCCESS;
        }
        return Constants.RESULT_STATUS_FAIL;
    }
}
