package org.paasta.caas.api.cluster.namespace;

import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * CLUSTER Service
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.8.01 최초작성
 */
@Service
public class NamespaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NamespaceService.class);
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;

    @Autowired
    public NamespaceService(RestTemplateService restTemplateService, CommonService commonService) {this.restTemplateService = restTemplateService;
        this.commonService = commonService;
    }

    Namespace getNamespaceList() {
        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_NAMESPACES_LIST, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getNamespaceList() :: hashMap.toString() :: {}", hashMap.toString());

        Namespace result = new Namespace();
        result.setResult(Constants.RESULT_STATUS_SUCCESS);
        result.setItems(commonService.setListData(Namespace.class, "metadata", (List) hashMap.get("items")));
//        result.setItems(setListData(new Namespace(), "metadata", (List) hashMap.get("items"))); // SAME RESULT

        return result;
    }



}
