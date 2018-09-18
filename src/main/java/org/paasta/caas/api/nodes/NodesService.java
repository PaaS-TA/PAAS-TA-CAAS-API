package org.paasta.caas.api.nodes;

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
 * Nodes Service 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.8.13
 */
@Service
public class NodesService {
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Nodes service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public NodesService(RestTemplateService restTemplateService, CommonService commonService,
                        PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }


    /**
     * Node 목록을 조회한다.
     *
     * @return the node list
     */
    NodesList getNodeList() {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListNodesListUrl(), HttpMethod.GET, null, Map.class);

        return (NodesList) commonService.setResultModel(commonService.setResultObject(resultMap, NodesList.class), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Node를 조회한다.
     *
     * @param nodeName the node name
     * @return the node
     */
    Nodes getNode(String nodeName) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListNodesGetUrl().replace("{name}", nodeName), HttpMethod.GET, null, Map.class);

        return (Nodes) commonService.setResultModel(commonService.setResultObject(resultMap, Nodes.class), Constants.RESULT_STATUS_SUCCESS);
    }

}
