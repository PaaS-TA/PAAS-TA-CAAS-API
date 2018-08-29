package org.paasta.caas.api.workloads.Deployments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.paasta.caas.api.workloads.deployments.Deployments;
import org.paasta.caas.api.workloads.deployments.DeploymentsList;
import org.paasta.caas.api.workloads.deployments.DeploymentsService;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")
public class DeploymentsServiceTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String DEPLOYMENT_NAME = "test-deployment-name";

    private static HashMap gResultMap = null;
    
    private static DeploymentsList gResultListModel = null;
    private static DeploymentsList gFinalResultListModel = null;
    private static DeploymentsList gFinalResultListFailModel = null;
    
    private static Deployments gResultModel = null;
    private static Deployments gFinalResultModel = null;
    private static Deployments gFinalResultFailModel = null;

    @Mock
    RestTemplateService restTemplateService;

    @Mock
    CommonService commonService;

    @Mock
    PropertyService propertyService;

    @InjectMocks
    DeploymentsService deploymentsService;

    @Before
    public void setUp() {

        // 리스트가져옴
        gResultMap = new HashMap();
        gResultListModel = new DeploymentsList();
        gFinalResultListModel = new DeploymentsList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);
        
        gFinalResultListFailModel = new DeploymentsList();
        gFinalResultListFailModel.setResultCode(Constants.RESULT_STATUS_FAIL);

        // 하나만 가져옴
        gResultModel = new Deployments();
        gFinalResultModel = new Deployments();
        gFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);
        
        gFinalResultFailModel = new Deployments();
        gFinalResultFailModel.setResultCode(Constants.RESULT_STATUS_FAIL);

    }

    @Test
    public void getDeploymentListByAllNamespace_Valid_ReturnModel() {

        //when 서술
        when(propertyService.getCaasMasterApiListDeploymentAllList()).thenReturn("/apis/apps/v1/deployments");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/deployments", HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, DeploymentsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        //실제 테스트할 함수 호출
        DeploymentsList resultList = deploymentsService.getDeploymentListByAllNamespace();
        //실제 결과 값 비교
        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultList.getResultCode());
    }


    @Test
    public void getDeploymentListByAllNamespace_Throw_Exception(){

        //when 서술
        when(propertyService.getCaasMasterApiListDeploymentAllList()).thenReturn("/apis/apps/v1/deployments");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/deployments", HttpMethod.GET, null, Map.class)).thenThrow(HttpClientErrorException.class);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_FAIL)).thenReturn(gFinalResultListFailModel);

        //실제 테스트할 함수 호출
        DeploymentsList resultList = deploymentsService.getDeploymentListByAllNamespace();

        //실제 결과 값 비교
        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_FAIL, resultList.getResultCode());
    }


    @Test
    public void getDeploymentList_Valid_ReturnModel() {

        //when
        when(propertyService.getCaasMasterApiListDeploymentList()).thenReturn("/apis/apps/v1/namespaces/{namespace}/deployments");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/namespaces/" + NAMESPACE +"/deployments", HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, DeploymentsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        //call method
        DeploymentsList resultList = deploymentsService.getDeploymentList(NAMESPACE);

        //compare result
        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultList.getResultCode());
    }

    @Test
    public void getDeploymentList_Throw_Exception() {

        //when
        when(propertyService.getCaasMasterApiListDeploymentList()).thenReturn("/apis/apps/v1/namespaces/{namespace}/deployments");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/namespaces/" + NAMESPACE +"/deployments", HttpMethod.GET, null, Map.class)).thenThrow(HttpClientErrorException.class);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_FAIL)).thenReturn(gFinalResultListFailModel);
        
        //call method
        DeploymentsList resultList = deploymentsService.getDeploymentList(NAMESPACE);

        //compare result
        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_FAIL, resultList.getResultCode());
    }


    @Test
    public void getDeployment_Valid_ReturnModel() {

        //when
        when(propertyService.getCaasMasterApiListDeploymentGet()).thenReturn("/apis/apps/v1/namespaces/{namespace}/deployments/{deploymentName}");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/namespaces/" + NAMESPACE +"/deployments/" + DEPLOYMENT_NAME, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, Deployments.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        //call method
        Deployments result = deploymentsService.getDeployment(NAMESPACE, DEPLOYMENT_NAME);

        //compare result
        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result.getResultCode());

    }

    @Test
    public void getDeployment_Throw_Exception() {

        //when
        when(propertyService.getCaasMasterApiListDeploymentGet()).thenReturn("/apis/apps/v1/namespaces/{namespace}/deployments/{deploymentName}");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/namespaces/" + NAMESPACE +"/deployments/" + DEPLOYMENT_NAME, HttpMethod.GET, null, Map.class)).thenThrow(HttpClientErrorException.class);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_FAIL)).thenReturn(gFinalResultFailModel);
        

        //call method
        Deployments result = deploymentsService.getDeployment(NAMESPACE, DEPLOYMENT_NAME);

        //compare result
        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_FAIL, result.getResultCode());

    }


}
