package org.paasta.caas.api.nodes;

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
 * The type Nodes service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class NodesServiceTest {
    private static final String NODE_NAME = "test-node-name";
    private static final String LIST_URL = "test-list-url";
    private static final String GET_URL = "test-get-url";

    private static HashMap gResultMap = null;
    private static NodesList gResultListModel = null;
    private static NodesList gFinalResultListModel = null;
    private static Nodes gResultModel = null;
    private static Nodes gFinalResultModel = null;

    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private CommonService commonService;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private NodesService nodesService;

    /**
     * Sets up
     */
    @Before
    public void setUp() {
        gResultMap = new HashMap();
        gResultListModel = new NodesList();
        gFinalResultListModel = new NodesList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gResultModel = new Nodes();
        gFinalResultModel = new Nodes();
        gFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {

    }

    /**
     * Nodes 목록을 조회할 때에 대한 테스트 케이스.
     */
    @Test
    public void getNodesList_Valid_ReturnModel() {
        when(propertyService.getCaasMasterApiListNodesListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                LIST_URL, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, NodesList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        NodesList resultModel = nodesService.getNodeList();

        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }

    /**
     * Nodes를 조회할 때에 대한 테스트 케이스.
     */
    @Test
    public void getNodes_Valid_ReturnModel() {
        when(propertyService.getCaasMasterApiListNodesGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                GET_URL, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, Nodes.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        Nodes resultModel = nodesService.getNode(NODE_NAME);
        
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }
}
