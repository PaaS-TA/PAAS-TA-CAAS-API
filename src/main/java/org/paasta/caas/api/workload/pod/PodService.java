package org.paasta.caas.api.workload.pod;

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
public class PodService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PodService.class);

    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Pod service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public PodService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * Gets pod list.
     *
     * @param namespace the namespace
     * @return the pod list
     */
    PodList getPodList(String namespace) {
        HashMap resultMap;
        if ("_all".equals( namespace )) {
            resultMap = (HashMap) restTemplateService.send( Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPodsAllListUrl(), HttpMethod.GET, null, Map.class );
        } else {
            resultMap = ( HashMap ) restTemplateService.send( Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListPodsListUrl().replace( "{namespace}", namespace ), HttpMethod.GET, null, Map.class );
        }

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (PodList) commonService.setResultModel(commonService.setResultObject(resultMap, PodList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Gets pod list.
     *
     * @param namespace the namespace
     * @param selector  the selector
     * @return the pod list
     */
    PodList getPodListWithLabelSelector(String namespace, String selector) {
        String requestSelector = "?labelSelector=" + selector;

        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
            propertyService.getCaasMasterApiListPodsListUrl().replace("{namespace}", namespace) + requestSelector, HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (PodList) commonService.setResultModel(commonService.setResultObject(resultMap, PodList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    PodList getPodListByNode ( String namespace, String nodeName, boolean isIncludedSucceededPods) {
        StringBuilder requestURLBuilder = new StringBuilder();
        if ( "_all".equals( namespace ) ) {
            requestURLBuilder.append( propertyService.getCaasMasterApiListPodsAllListUrl() );
        } else {
            requestURLBuilder.append(
                propertyService.getCaasMasterApiListPodsListUrl().replace( "{namespace}", namespace ) );
        }
        requestURLBuilder.append( "/?fieldSelector=spec.nodeName=" ).append( nodeName );
        if ( !isIncludedSucceededPods )
            requestURLBuilder.append( ",status.phase!=Succeeded" );

        HashMap resultMap = ( HashMap ) restTemplateService.send( Constants.TARGET_CAAS_MASTER_API,
            requestURLBuilder.toString(), HttpMethod.GET, null, Map.class );

        LOGGER.info( "########## resultMap.toString() :: {}", resultMap.toString() );

        return ( PodList ) commonService.setResultModel( commonService.setResultObject( resultMap, PodList.class ), Constants.RESULT_STATUS_SUCCESS );
    }

    /**
     * Get pod.
     * @param namespace the namespace
     * @param podName the pod's name
     * @return
     */
    Pod getPod ( String namespace, String podName ) {
        HashMap resultMap = (HashMap) restTemplateService.send( Constants.TARGET_CAAS_MASTER_API,
            propertyService.getCaasMasterApiListPodsGetUrl().replace( "{namespace}", namespace ).replace( "{name}", podName ),
            HttpMethod.GET, null, Map.class );

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (Pod) commonService.setResultModel(commonService.setResultObject(resultMap, Pod.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
