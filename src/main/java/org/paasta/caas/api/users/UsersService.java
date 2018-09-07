package org.paasta.caas.api.users;

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
 * @author hrjin
 * @version 1.0
 * @since 2018-09-03
 */
@Service
public class UsersService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RolesService.class);
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;

    @Autowired
    public UsersService(RestTemplateService restTemplateService, CommonService commonService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
    }

    @Autowired
    PropertyService propertyService;


    /**
     * Service Account 를 삭제한다.
     *
     * @param namespace
     * @param caasAccountName
     * @return
     */
    public Users deleteServiceAccount(String namespace, String caasAccountName) {
        Users responseObject;
        String resultCode;

        String apiUrl = propertyService.getCaasMasterApiListUsersDeleteUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace)
                .replaceAll("\\{" + "name" + "\\}", caasAccountName);

        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.DELETE, null, Map.class);
        LOGGER.info("########## deleteServiceAccount() :: responseMap.toString() :: {}", responseMap.toString());

        responseObject = commonService.setResultObject(responseMap, Users.class);
        resultCode = Constants.RESULT_STATUS_SUCCESS;

        return (Users) commonService.setResultModel(responseObject, resultCode);
    }
}
