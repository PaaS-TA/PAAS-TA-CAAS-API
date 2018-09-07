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
import org.paasta.caas.api.events.EventsList;
import org.paasta.caas.api.events.EventsService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type Custom services service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class EventsServiceTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String FIELD_SELECTOR = "?fieldSelector=involvedObject.name=";
    private static final String RESOURCE_NAME = "test-resource-name";
    private static final String LIST_URL = "test-list-url";

    private static HashMap gResultMap = null;
    private static EventsList gResultListModel = null;
    private static EventsList gFinalResultListModel = null;

    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private CommonService commonService;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private EventsService eventsService;


    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        gResultMap = new HashMap();
        gResultListModel = new EventsList();
        gFinalResultListModel = new EventsList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Tear down.
     */
    @After
    public void tearDown() {
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////// MethodName_StateUnderTest_ExpectedBehavior
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Gets event list valid return model.
     */
    @Test
    public void getEventList_Valid_ReturnModel() {
        // CONDITION

        when(propertyService.getCaasMasterApiListEventsListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, LIST_URL
                .replace("{namespace}", NAMESPACE) + FIELD_SELECTOR +RESOURCE_NAME, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, EventsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        // TEST
        EventsList resultModel = eventsService.getEventList(NAMESPACE,RESOURCE_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


}
