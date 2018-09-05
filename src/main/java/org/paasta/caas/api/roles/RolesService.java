package org.paasta.caas.api.roles;

import org.paasta.caas.api.common.CommonService;
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
    private final CommonService commonService;

    @Autowired
    public RolesService(RestTemplateService restTemplateService, CommonService commonService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
    }

    @Autowired
    PropertyService propertyService;


    /**
     * RoleList 조회
     *
     * @param namespace
     * @return Map
     */
    public RolesList getRoleList(String namespace) {
        RolesList rolesList;
        String resultCode;

        String apiUrl = propertyService.getCaasMasterApiListRolesListUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleList() :: responseMap.toString() :: {}", responseMap.toString());

        rolesList = commonService.setResultObject(responseMap, RolesList.class);
        resultCode = Constants.RESULT_STATUS_SUCCESS;

        return (RolesList) commonService.setResultModel(rolesList, resultCode);
    }


    /**
     * Role 상세 조회
     *
     * @param namespace
     * @param roleName
     * @return Map
     */
    public Roles getRole(String namespace, String roleName) {
        Roles roles;
        String resultCode;

        String apiUrl = propertyService.getCaasMasterApiListRolesGetUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", roleName);

        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRole() :: responseMap.toString() :: {}", responseMap.toString());

        roles = commonService.setResultObject(responseMap, Roles.class);
        resultCode = Constants.RESULT_STATUS_SUCCESS;

        return (Roles) commonService.setResultModel(roles, resultCode);
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

        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.DELETE, null, Map.class);
        LOGGER.info("########## deleteRole() :: responseMap.toString() :: {}", responseMap.toString());

        if(responseMap != null){
            return Constants.RESULT_STATUS_SUCCESS;
        }
        return Constants.RESULT_STATUS_FAIL;
    }

}
