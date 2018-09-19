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

    @Before
    public void setUp() {

        // 리스트가져옴
        gResultMap = new HashMap();
        gResultListModel = new PodsList();
        gFinalResultListModel = new PodsList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gFinalResultListFailModel = new PodsList();
        gFinalResultListFailModel.setResultCode(Constants.RESULT_STATUS_FAIL);

        // 하나만 가져옴
        gResultModel = new Pods();
        gFinalResultModel = new Pods();
        gFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gFinalResultFailModel = new Pods();
        gFinalResultFailModel.setResultCode(Constants.RESULT_STATUS_FAIL);

    }

    @Test
    public void getPodsList_Valid_ReturnModel() {

        //when 서술
        when(propertyService.getCaasMasterApiListPodsListUrl()).thenReturn("/apis/apps/v1/pods");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/pods", HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, PodsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        //실제 테스트할 함수 호출
        PodsList resultList = podsService.getPodList(NAMESPACE);
        //실제 결과 값 비교
        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultList.getResultCode());
    }

    @Test
    public void getPodListWithLabelSelector_Valid_ReturnModel() {

        //when 서술
        when(propertyService.getCaasMasterApiListPodsListUrl()).thenReturn("/apis/apps/v1/pods");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/pods?labelSelector=" + SELECTOR, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, PodsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        //실제 테스트할 함수 호출
        PodsList resultList = podsService.getPodListWithLabelSelector(NAMESPACE, SELECTOR);
        //실제 결과 값 비교
        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultList.getResultCode());
    }


    @Test
    public void getPodListByNode_NAMESPACE_Valid_ReturnModel() {

        //when 서술
        when(propertyService.getCaasMasterApiListPodsListUrl()).thenReturn("/api/v1/namespaces/{namespace}/pods");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/api/v1/namespaces/" + NAMESPACE + "/pods/?fieldSelector=spec.nodeName=" + NODE_NAME, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, PodsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        //실제 테스트할 함수 호출
        PodsList resultList = podsService.getPodListByNode(NAMESPACE, NODE_NAME, true);
        //실제 결과 값 비교
        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultList.getResultCode());

    }

    @Test
    public void getPodListByNode_NAMESPACE_False_Valid_ReturnModel() {

        //when 서술  /api/v1/namespaces/{namespace}/pods
        when(propertyService.getCaasMasterApiListPodsListUrl()).thenReturn("/api/v1/namespaces/{namespace}/pods");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/api/v1/namespaces/" + NAMESPACE + "/pods/?fieldSelector=spec.nodeName=" + NODE_NAME + ",status.phase!=Succeeded", HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, PodsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        //실제 테스트할 함수 호출
        PodsList resultList = podsService.getPodListByNode(NAMESPACE, NODE_NAME, false);
        //실제 결과 값 비교
        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultList.getResultCode());

    }

    @Test
    public void getPod_Valid_ReturnModel() {

        //when
        when(propertyService.getCaasMasterApiListPodsGetUrl()).thenReturn("/apis/apps/v1/namespaces/{namespace}/pods/{name}");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/namespaces/" + NAMESPACE + "/pods/" + PODS_NAME, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, Pods.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        //call method
        Pods result = podsService.getPod(NAMESPACE, PODS_NAME);

        //compare result
        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result.getResultCode());

    }
}
