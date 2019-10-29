package org.paasta.caas.api.persistentVolumeClaims;

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
 * PersistentVolumeClaims Service 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-10-24
 */
@Service
public class PersistentVolumeClaimsService {

    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new deployments service.
     *
     * @param restTemplateService the rest template service
     * @param commonService        the common service
     * @param propertyService      the property service
     */
    @Autowired
    public PersistentVolumeClaimsService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }


    /**
     * PersistentVolumeClaims 목록을 조회한다.
     *
     * @param namespace the namespace
     * @return the PersistentVolumeClaims List
     */
    public PersistentVolumeClaimsList getPersistentVolumeClaimsList(String namespace) {
        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPersistentVolumeClaimsListUrl()
                        .replace("{namespace}", namespace)
                , HttpMethod.GET, null, Map.class);

        return (PersistentVolumeClaimsList) commonService.setResultModel(commonService.setResultObject(responseMap, PersistentVolumeClaimsList.class), Constants.RESULT_STATUS_SUCCESS);

    }

    /**
     * PersistentVolumeClaims 상세 정보를 조회한다.
     *
     * @param namespace                     the namespace
     * @param persistentVolumeClaimName  the PersistentVolumeClaim name
     * @return the PersistentVolumeClaims
     */
    public PersistentVolumeClaims getPersistentVolumeClaims(String namespace, String persistentVolumeClaimName) {
        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPersistentVolumeClaimsGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", persistentVolumeClaimName)
                , HttpMethod.GET, null, Map.class);

        return (PersistentVolumeClaims) commonService.setResultModel(commonService.setResultObject(responseMap, PersistentVolumeClaims.class), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * PersistentVolumeClaims YAML 을 조회한다.
     *
     * @param namespace                    the namespace
     * @param persistentVolumeClaimName the PersistentVolumeClaim name
     * @param resultMap                    the result map
     * @return the PersistentVolumeClaims
     */
    public PersistentVolumeClaims getPersistentVolumeClaimsYaml(String namespace, String persistentVolumeClaimName, HashMap resultMap) {
        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPersistentVolumeClaimsGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", persistentVolumeClaimName), HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML);

        //noinspection unchecked
        resultMap.put("sourceTypeYaml", resultString);

        return (PersistentVolumeClaims) commonService.setResultModel(commonService.setResultObject(resultMap, PersistentVolumeClaims.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
