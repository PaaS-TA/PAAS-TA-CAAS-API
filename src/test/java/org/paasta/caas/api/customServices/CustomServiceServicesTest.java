package org.paasta.caas.api.customServices;

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
 * The type Custom services service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class CustomServiceServicesTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String SERVICE_NAME = "test-service-name";
    private static final String LIST_URL = "test-list-url";
    private static final String GET_URL = "test-get-url";
    private static final String YAML_STRING = "test-yaml-string";

    private static HashMap gResultMap = null;
    private static CustomServicesList gResultListModel = null;
    private static CustomServicesList gFinalResultListModel = null;
    private static CustomServices gResultModel = null;
    private static CustomServices gFinalResultModel = null;


    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private CommonService commonService;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private CustomServicesService customServicesService;


    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        gResultMap = new HashMap();
        gResultListModel = new CustomServicesList();
        gFinalResultListModel = new CustomServicesList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gResultModel = new CustomServices();
        gFinalResultModel = new CustomServices();
        gFinalResultModel.setSourceTypeYaml(YAML_STRING);
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
     * Gets custom services list valid return model.
     */
    @Test
    public void getCustomServicesList_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListServicesListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, LIST_URL
                .replace("{namespace}", NAMESPACE), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, CustomServicesList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        // TEST
        CustomServicesList resultModel = customServicesService.getCustomServicesList(NAMESPACE);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Gets custom services valid return model.
     */
    @Test
    public void getCustomServices_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListServicesGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", SERVICE_NAME), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, CustomServices.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        // TEST
        CustomServices resultModel = customServicesService.getCustomServices(NAMESPACE, SERVICE_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Gets custom services yaml valid return model.
     */
    @Test
    public void getCustomServicesYaml_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListServicesGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", SERVICE_NAME), HttpMethod.GET, null, String.class)).thenReturn(YAML_STRING);
        when(commonService.setResultObject(gResultMap, CustomServices.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        // TEST
        CustomServices resultModel = customServicesService.getCustomServicesYaml(NAMESPACE, SERVICE_NAME, gResultMap);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(YAML_STRING, resultModel.getSourceTypeYaml());
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }

}
