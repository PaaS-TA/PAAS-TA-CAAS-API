package org.paasta.caas.api.endpoint;

import org.assertj.core.api.Assertions;
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
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type Endpoint service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class EndpointServiceTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String SERVICE_NAME = "test-service-name";
    private static final String LIST_URL = "test-list-url";
    private static final String GET_URL = "test-get-url";
    private static final String gResultListMapString = "test-result-list-map-string";
    private static final String gResultMapString = "test-result-map-string";

    private static HashMap gResultMap = null;
    private static EndpointList gResultListModel = null;
    private static EndpointList gFinalResultListModel = null;
    private static Endpoint gResultModel = null;
    private static Endpoint gFinalResultModel = null;


    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private CommonService commonService;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private EndpointService endpointService;


    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        gResultMap = new HashMap();
        gResultListModel = new EndpointList();
        gFinalResultListModel = new EndpointList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gResultModel = new Endpoint();
        gFinalResultModel = new Endpoint();
        gFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);
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
     * Gets endpoint list valid return model.
     */
    @Test
    public void getEndpointList_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListEndpointsListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, LIST_URL
                .replace("{namespace}", NAMESPACE), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, EndpointList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        // TEST
        EndpointList resultModel = endpointService.getEndpointList(NAMESPACE);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Gets endpoint valid return model.
     */
    @Test
    public void getEndpoint_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListEndpointsGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", SERVICE_NAME), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, Endpoint.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        // TEST
        Endpoint resultModel = endpointService.getEndpoint(NAMESPACE, SERVICE_NAME);

        // VERIFY
        Assertions.assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }

}
