package org.paasta.caas.api.workloads.replicaSets;

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
 * The type ReplicaSets test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class ReplicaSetsServiceTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String SERVICE_NAME = "test-service-name";
    private static final String LIST_URL = "test-list-url";
    private static final String GET_URL = "test-get-url";
    private static final String YAML_STRING = "test-yaml-string";
    private static final String LABEL_SELECTOR = "test-label-selector";


    private static HashMap gResultMap = null;
    private static ReplicaSetsList gResultListModel = null;
    private static ReplicaSetsList gFinalResultListModel = null;
    private static ReplicaSets gResultModel = null;
    private static ReplicaSets gFinalResultModel = null;


    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private CommonService commonService;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private ReplicaSetsService replicaSetsService;


    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        gResultMap = new HashMap();
        gResultMap.put("sourceTypeYaml", YAML_STRING);
        gResultListModel = new ReplicaSetsList();
        gFinalResultListModel = new ReplicaSetsList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gResultModel = new ReplicaSets();
        gFinalResultModel = new ReplicaSets();
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
    public void getReplicaSetsList_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListReplicaSetsListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, LIST_URL
                .replace("{namespace}", NAMESPACE), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, ReplicaSetsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        // TEST
        ReplicaSetsList resultModel = replicaSetsService.getReplicaSetsList(NAMESPACE);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Gets custom services valid return model.
     */
    @Test
    public void getReplicaSets_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListReplicaSetsGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", SERVICE_NAME), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, ReplicaSets.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        // TEST
        ReplicaSets resultModel = replicaSetsService.getReplicaSets(NAMESPACE, SERVICE_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Gets custom services yaml valid return model.
     */
    @Test
    public void getReplicaSetsYaml_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListReplicaSetsGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", SERVICE_NAME), HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML)).thenReturn(YAML_STRING);
        //HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML resultMap, ReplicaSets.class
        when(commonService.setResultObject(gResultMap, ReplicaSets.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        // TEST
        ReplicaSets resultModel = replicaSetsService.getReplicaSetsYaml(NAMESPACE, SERVICE_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(YAML_STRING, resultModel.getSourceTypeYaml());
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Gets custom services list valid return model.
     */
    @Test
    public void getReplicaSetsListLabelSelector_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListReplicaSetsListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, LIST_URL
                .replace("{namespace}", NAMESPACE) + "?labelSelector=" + LABEL_SELECTOR, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, ReplicaSetsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        // TEST
        ReplicaSetsList resultModel = replicaSetsService.getReplicaSetsListLabelSelector(NAMESPACE, LABEL_SELECTOR);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }

}
