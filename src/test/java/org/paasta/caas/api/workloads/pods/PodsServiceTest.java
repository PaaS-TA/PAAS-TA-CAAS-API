package org.paasta.caas.api.workloads.pods;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")
public class PodsServiceTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String PODS_NAME = "test-pods-name";
    private static final String SELECTOR = "test-selector";
    private static final String NODE_NAME = "test-node";
    private static final String YAML_STRING = "test-yaml-string";

    private static HashMap gResultMap = null;

    private static PodsList gResultListModel = null;
    private static PodsList gFinalResultListModel = null;
    private static PodsList gFinalResultListFailModel = null;

    private static Pods gResultModel = null;
    private static Pods gFinalResultModel = null;
    private static Pods gFinalResultFailModel = null;

    @Mock
    RestTemplateService restTemplateService;

    @Mock
    CommonService commonService;

    @Mock
    PropertyService propertyService;

    @InjectMocks
    PodsService podsService;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        gResultMap = new HashMap();
        gResultListModel = new PodsList();
        gFinalResultListModel = new PodsList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gFinalResultListFailModel = new PodsList();
        gFinalResultListFailModel.setResultCode(Constants.RESULT_STATUS_FAIL);

        gResultModel = new Pods();
        gFinalResultModel = new Pods();
        gFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gFinalResultFailModel = new Pods();
        gFinalResultFailModel.setResultCode(Constants.RESULT_STATUS_FAIL);

    }

    /**
     * Pods 목록을 조회할 때에 대한 테스트 케이스.
     */
    @Test
    public void getPodsList_Valid_ReturnModel() {
        when(propertyService.getCaasMasterApiListPodsListUrl()).thenReturn("/apis/apps/v1/pods");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/pods", HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, PodsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        PodsList resultList = podsService.getPodList(NAMESPACE);

        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultList.getResultCode());
    }

    /**
     * Label Selector를 이용해 Pods 목록을 조회할 때에 대한 테스트 케이스.
     */
    @Test
    public void getPodListWithLabelSelector_Valid_ReturnModel() {
        when(propertyService.getCaasMasterApiListPodsListUrl()).thenReturn("/apis/apps/v1/pods");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/pods?labelSelector=" + SELECTOR, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, PodsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);


        PodsList resultList = podsService.getPodListWithLabelSelector(NAMESPACE, SELECTOR);

        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultList.getResultCode());
    }

    /**
     * Node 이름을 이용해 Pods 목록을 조회할 때에 대한 테스트 케이스.
     */
    @Test
    public void getPodListByNode_NAMESPACE_Valid_ReturnModel() {
        when(propertyService.getCaasMasterApiListPodsListUrl()).thenReturn("/api/v1/namespaces/{namespace}/pods");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/api/v1/namespaces/" + NAMESPACE + "/pods/?fieldSelector=spec.nodeName=" + NODE_NAME, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, PodsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        PodsList resultList = podsService.getPodListByNode(NAMESPACE, NODE_NAME);

        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultList.getResultCode());

    }

    /**
     * Pods를 조회할 때에 대한 테스트 케이스.
     */
    @Test
    public void getPod_Valid_ReturnModel() {
        when(propertyService.getCaasMasterApiListPodsGetUrl()).thenReturn("/apis/apps/v1/namespaces/{namespace}/pods/{name}");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/namespaces/" + NAMESPACE + "/pods/" + PODS_NAME, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, Pods.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        Pods result = podsService.getPod(NAMESPACE, PODS_NAME);

        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result.getResultCode());
    }

    /**
     * Pods의 원본 YAML을 조회할 때에 대한 테스트 케이스.
     */
    @Test
    public void getPodYaml_Valid_ReturnModel() {
        gResultMap.put("sourceTypeYaml", YAML_STRING);
        gResultModel.setSourceTypeYaml(YAML_STRING);
        gFinalResultModel.setSourceTypeYaml(YAML_STRING);

        when(propertyService.getCaasMasterApiListPodsGetUrl()).thenReturn("/apis/apps/v1/namespaces/{namespace}/pods/{name}");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/namespaces/" + NAMESPACE + "/pods/" + PODS_NAME, HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML)).thenReturn(YAML_STRING);
        when(commonService.setResultObject(gResultMap, Pods.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        Pods result = podsService.getPodYaml(NAMESPACE, PODS_NAME);

        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result.getResultCode());
        assertEquals(YAML_STRING, result.getSourceTypeYaml());
    }
}
