package org.paasta.caas.api.customService;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type Custom service service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class CustomServiceServiceTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String SERVICE_NAME = "test-service-name";
    private static final String LIST_URL = "test-list-url";
    private static final String GET_URL = "test-get-url";
    private static final String gResultListMapString = "test-result-list-map-string";
    private static final String gResultMapString = "test-result-map-string";

    private static HashMap gResultMap = null;
    private static CustomServiceList gResultListModel = null;
    private static CustomServiceList gFinalResultListModel = null;
    private static CustomService gResultModel = null;
    private static CustomService gFinalResultModel = null;


    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private CommonService commonService;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private CustomServiceService customServiceService;


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {

        gResultMap = new HashMap();
        gResultListModel = new CustomServiceList();
        gFinalResultListModel = new CustomServiceList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gResultModel = new CustomService();
        gFinalResultModel = new CustomService();
        gFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////// MethodName_StateUnderTest_ExpectedBehavior
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Gets custom service list valid return model.
     */
    @Test
    public void getCustomServiceList_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListServicesListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, LIST_URL
                .replace("{namespace}", NAMESPACE), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.toJson(gResultMap)).thenReturn(gResultListMapString);
        when(commonService.fromJson(gResultListMapString, CustomServiceList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        // TEST
        CustomServiceList resultModel = customServiceService.getCustomServiceList(NAMESPACE);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Gets custom service valid return model.
     */
    @Test
    public void getCustomService_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListServicesGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", SERVICE_NAME), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.toJson(gResultMap)).thenReturn(gResultMapString);
        when(commonService.fromJson(gResultMapString, CustomService.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        // TEST
        CustomService resultModel = customServiceService.getCustomService(NAMESPACE, SERVICE_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }

}
