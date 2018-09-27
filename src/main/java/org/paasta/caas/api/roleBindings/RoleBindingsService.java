package org.paasta.caas.api.roleBindings;

import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * RoleBinding Service 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018 -08-17
 */
@Service
public class RoleBindingsService {

    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new RoleBinding service
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public RoleBindingsService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }


    /**
     * RoleBindingList 조회 (특정 네임스페이스에서 조회)
     *
     * @param namespace the namespace
     * @return the RoleBindingsList
     */
    RoleBindingsList getRoleBindingList(String namespace) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsListUrl().replace("{namespace}", namespace);

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        return (RoleBindingsList) commonService.setResultModel(commonService.setResultObject(responseMap, RoleBindingsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * RoleBinding 객체를 조회한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace       the namespace
     * @param roleBindingName the roleBindingName
     * @return the RoleBindings
     */
    RoleBindings getRoleBinding(String namespace, String roleBindingName) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsGetUrl()
                .replace("{namespace}", namespace)
                .replace("{name}", roleBindingName);

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        return (RoleBindings) commonService.setResultModel(commonService.setResultObject(responseMap, RoleBindings.class), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * RoleBinding 권한을 할당한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace   the namespace
     * @param roleBinding the roleBinding
     * @return the RoleBindings
     */
    RoleBindings createRoleBinding(String namespace, RoleBindings roleBinding) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsCreateUrl().replace("{namespace}", namespace);

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.POST, roleBinding, Map.class);
        return (RoleBindings) commonService.setResultModel(commonService.setResultObject(responseMap, RoleBindings.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * RoleBinding 권한을 해지한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace       the namespace
     * @param roleBindingName the roleBindingName
     * @return String string
     */
    String deleteRoleBinding(String namespace, String roleBindingName) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsDeleteUrl()
                .replace("{namespace}", namespace)
                .replace("{name}", roleBindingName);

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.DELETE, null, Map.class);

        if (responseMap.get("status").equals("Success")) {
            return Constants.RESULT_STATUS_SUCCESS;
        }
        return Constants.RESULT_STATUS_FAIL;
    }

    /**
     * RoleBinding 권한을 수정한다.
     *
     * @param namespace       the namespace
     * @param roleBindingName the roleBindingName
     * @param yml             the yml
     * @return String string
     */
    String updateRoleBinding(String namespace, String roleBindingName, String yml) {
        String apiUrl = propertyService.getCaasMasterApiListRoleBindingsUpdateUrl()
                .replace("{namespace}", namespace)
                .replace("{name}", roleBindingName);

        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.PUT, yml, String.class,
                Constants.ACCEPT_TYPE_YAML, Constants.ACCEPT_TYPE_YAML);

        if (resultString != null) {
            return Constants.RESULT_STATUS_SUCCESS;
        }
        return Constants.RESULT_STATUS_FAIL;
    }
}
