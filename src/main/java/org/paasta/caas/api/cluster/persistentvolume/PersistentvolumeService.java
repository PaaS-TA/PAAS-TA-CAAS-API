package org.paasta.caas.api.cluster.persistentvolume;

import com.google.gson.Gson;
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
 * CLUSTER Service
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.8.01 최초작성
 */
@Service
public class PersistentvolumeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistentvolumeService.class);
    private final CommonService commonService;
    private final PropertyService propertyService;
    private final RestTemplateService restTemplateService;

    /**
     * Instantiates a new Custom service service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public PersistentvolumeService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * PersistentVolume List 조회
     *
     * @return PersistentVolumeList
     */
    PersistentvolumeList getPersistentvolumeList() {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPersistentvolumesListUrl(), HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (PersistentvolumeList) commonService.setResultModel(new Gson().fromJson(new Gson().toJson(resultMap), PersistentvolumeList.class), Constants.RESULT_STATUS_SUCCESS, "");
    }

    /**
     * PersistentVolume 상세 조회
     *
     * @return PersistentVolume
     */
    Persistentvolume getPersistentvolume(String pvcName) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPersistentvolumesGetUrl()
                        .replaceAll("\\{" + "name" + "\\}", pvcName), HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (Persistentvolume) commonService.setResultModel(new Gson().fromJson(new Gson().toJson(resultMap), Persistentvolume.class), Constants.RESULT_STATUS_SUCCESS, "");
    }
}
