package org.paasta.caas.api.events;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type Nodes service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class EventsServiceTest {
    private static final String NAMESPACE = "test-namespace";
    private static final String ALL_NAMESPACE = "_all";
    private static final String RESOURCE = "test-resource";
    private static final String NODE_NAME = "test-node";
    private static final String LIST_URL = "test-list-url";
    private static final String EVENTLIST_RESULT_KEY = "items";
    private static final String SOURCEHOST_METAKEY = "source";
    private static final String SOURCEHOST_KEY = "host";
    private static final String SOURCEHOST_VALUE = NODE_NAME;
    private static final String TYPE = "type";

    private static HashMap gResultMap = null;
    private static EventsList gResultListModel = null;
    private static EventsList gFinalResultListModel = null;

    private static List<Map<String, Object>> gOriginalMapList = null;
    private static List<Map<String, Object>> gFilterMapList = null;
    private static Map<String, Object> gOriginalMap = null;
    private static Map<String, Object> gSource = null;

    private static Map<String, Object> gOriginalMapAnotherHost = null;
    private static Map<String, Object> gSourceAnotherHost = null;

    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private CommonService commonService;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private EventsService eventsService;

    /**
     * Sets up
     */
    @Before
    public void setUp() {
        gResultMap = new HashMap();
        gResultListModel = new EventsList();
        gFinalResultListModel = new EventsList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gOriginalMapList = new LinkedList<>();
        gFilterMapList = new LinkedList<>();

        // items
        gOriginalMap = new HashMap<>();
        gOriginalMapAnotherHost = new HashMap<>();
        gOriginalMapList.add(gOriginalMap);
        gOriginalMapList.add(gOriginalMapAnotherHost);

        // source
        gSource = new HashMap<>();
        gSourceAnotherHost = new HashMap<>();
        gSource.put(SOURCEHOST_KEY, SOURCEHOST_VALUE);
        gSourceAnotherHost.put(SOURCEHOST_KEY, SOURCEHOST_VALUE + "2");
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {

    }

    @Test
    public void getEventList_Type_Null_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListEventsListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                LIST_URL + "?fieldSelector=involvedObject.uid=" + RESOURCE, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, EventsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        // TEST
        EventsList resultModel = eventsService.getEventsList(NAMESPACE, RESOURCE, null);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }

    @Test
    public void getEventList_Type_Not_Null_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListEventsListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                LIST_URL + "?fieldSelector=involvedObject.name=" + RESOURCE, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, EventsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        // TEST
        EventsList resultModel = eventsService.getEventsList(NAMESPACE, RESOURCE, TYPE);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }

    @Test
    public void getNamespaceEventList_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListEventsListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                LIST_URL, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, EventsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        // TEST
        EventsList resultModel = eventsService.getNamespaceEventsList(NAMESPACE);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }
}
