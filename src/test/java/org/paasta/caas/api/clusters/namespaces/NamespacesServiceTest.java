package org.paasta.caas.api.clusters.namespaces;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.RestTemplateService;
import org.paasta.caas.api.workloads.deployments.DeploymentsList;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class NamespacesServiceTest {

    private static final String NAMESPACE = "test-namespace";

    private static HashMap resultMap = null;
    private static Namespaces nResultModel = null;
    private static Namespaces nFinalResultModel = null;
    private static Namespaces nFinalResultFailModel = null;

    private static ResourceQuotaList rqResultModel = null;
    private static ResourceQuotaList rqFinalResultModel = null;
    private static ResourceQuotaList rqFinalResultFailModel = null;

    @Mock
    RestTemplateService restTemplateService;
    @Mock
    CommonService commonService;
    @InjectMocks
    NamespacesService namespacesService;

    @Before
    public void setUp() {
        resultMap = new HashMap();

        nResultModel = new Namespaces();

        nFinalResultModel = new Namespaces();
        nFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        nFinalResultFailModel = new Namespaces();
        nFinalResultFailModel.setResultCode(Constants.RESULT_STATUS_FAIL);

        rqResultModel = new ResourceQuotaList();

        rqFinalResultModel = new ResourceQuotaList();
        rqFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        rqFinalResultFailModel = new ResourceQuotaList();
        rqFinalResultFailModel.setResultCode(Constants.RESULT_STATUS_FAIL);

//        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetNamespaces() {
        // CONDITION
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/api/v1/namespaces/"+NAMESPACE, HttpMethod.GET, null, Map.class)).thenReturn(resultMap);
        when(commonService.setResultModel(nResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(nFinalResultModel);
        when(commonService.setResultObject(resultMap, Namespaces.class)).thenReturn(nResultModel);

        // TEST
        Namespaces result = namespacesService.getNamespaces(NAMESPACE);

        // VERIFY
        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result.getResultCode());
    }

    @Test
    public void testGetResourceQuotaList() {
        //when
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/api/v1/namespaces/"+NAMESPACE+"/resourcequotas", HttpMethod.GET, null, Map.class)).thenReturn(resultMap);
        when(commonService.setResultModel(rqResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(rqFinalResultModel);
        when(commonService.setResultObject(resultMap, ResourceQuotaList.class)).thenReturn(rqResultModel);

        // TEST
        ResourceQuotaList result = namespacesService.getResourceQuotaList(NAMESPACE);

        // VERIFY
        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result.getResultCode());
    }
}