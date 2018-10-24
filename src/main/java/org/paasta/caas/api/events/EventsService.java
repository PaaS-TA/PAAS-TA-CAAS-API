package org.paasta.caas.api.events;

import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Events Service 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.13
 */
@Service
public class EventsService {

    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Events service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public EventsService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * Events 목록을 조회한다.
     *
     * @param namespace    the namespace
     * @param resourceUid the resourceUid
     * @return the events list
     */
    EventsList getEventsList(String namespace, String resourceUid, String type) {
        String requestSelector = "?fieldSelector=involvedObject.uid=" + resourceUid;

        if(type != null) {
            requestSelector = "?fieldSelector=involvedObject.name=" + resourceUid;
        }

        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListEventsListUrl()
                        .replace("{namespace}", namespace) + requestSelector, HttpMethod.GET, null, Map.class);

        return (EventsList) commonService.setResultModel(
                commonService.setResultObject(resultMap, EventsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Events 목록을 조회한다.(for namespace)
     *
     * @param namespace    the namespace
     * @return the events list
     */
    EventsList getNamespaceEventsList(String namespace) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListEventsListUrl()
                        .replace("{namespace}", namespace), HttpMethod.GET, null, Map.class);

        return (EventsList) commonService.setResultModel(
                commonService.setResultObject(resultMap, EventsList.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
