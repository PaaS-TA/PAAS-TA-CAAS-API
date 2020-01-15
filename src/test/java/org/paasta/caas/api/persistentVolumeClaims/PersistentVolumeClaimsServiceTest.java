package org.paasta.caas.api.persistentVolumeClaims;

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
public class PersistentVolumeClaimsServiceTest {
    private static final String NAMESPACE = "test-namespace";
    private static final String PERSISTENT_VOLUME_CLAIM_NAME = "test-pvc-name";
    private static final String YAML_STRING = "test-yaml-string";

    private static HashMap gResultMap = null;

    private static PersistentVolumeClaimsList gResultListModel = null;
    private static PersistentVolumeClaimsList gFinalResultListModel = null;
    private static PersistentVolumeClaimsList gFinalResultListFailModel = null;

    private static PersistentVolumeClaims gResultModel = null;
    private static PersistentVolumeClaims gFinalResultModel = null;
    private static PersistentVolumeClaims gFinalResultFailModel = null;

    @Mock
    RestTemplateService restTemplateService;

    @Mock
    CommonService commonService;

    @Mock
    PropertyService propertyService;

    @InjectMocks
    PersistentVolumeClaimsService persistentVolumeClaimsService;

    @Before
    public void setUp() {

        // 리스트가져옴
        gResultMap = new HashMap();
        gResultListModel = new PersistentVolumeClaimsList();
        gFinalResultListModel = new PersistentVolumeClaimsList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gFinalResultListFailModel = new PersistentVolumeClaimsList();
        gFinalResultListFailModel.setResultCode(Constants.RESULT_STATUS_FAIL);

        // 하나만 가져옴
        gResultModel = new PersistentVolumeClaims();
        gFinalResultModel = new PersistentVolumeClaims();
        gFinalResultModel.setSourceTypeYaml(YAML_STRING);
        gFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gFinalResultFailModel = new PersistentVolumeClaims();
        gFinalResultFailModel.setResultCode(Constants.RESULT_STATUS_FAIL);

    }

    @Test
    public void getPersistentVolumeClaimsList_Valid_ReturnModel() {

        //when
        when(propertyService.getCaasMasterApiListPersistentVolumeClaimsListUrl()).thenReturn("/api/v1/namespaces/{namespace}/persistentvolumeclaims");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/api/v1/namespaces/" + NAMESPACE +"/persistentvolumeclaims", HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, PersistentVolumeClaimsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        //call method
        PersistentVolumeClaimsList resultList = persistentVolumeClaimsService.getPersistentVolumeClaimsList(NAMESPACE);

        //compare result
        assertThat(resultList).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultList.getResultCode());
    }


    @Test
    public void getPersistentVolumeClaim_Valid_ReturnModel() {

        //when
        when(propertyService.getCaasMasterApiListPersistentVolumeClaimsGetUrl()).thenReturn("/api/v1/namespaces/{namespace}/persistentvolumeclaims/{name}");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/api/v1/namespaces/" + NAMESPACE +"/persistentvolumeclaims/" + PERSISTENT_VOLUME_CLAIM_NAME, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, PersistentVolumeClaims.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        //call method
        PersistentVolumeClaims result = persistentVolumeClaimsService.getPersistentVolumeClaims(NAMESPACE, PERSISTENT_VOLUME_CLAIM_NAME);

        //compare result
        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result.getResultCode());

    }

    @Test
    public void getPersistentVolumeClaim_Yaml_Valid_ReturnModel() {

        //when
        when(propertyService.getCaasMasterApiListPersistentVolumeClaimsGetUrl()).thenReturn("/apis/apps/v1/namespaces/{namespace}/persistentvolumeclaims/{name}");
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/apis/apps/v1/namespaces/" + NAMESPACE +"/persistentvolumeclaims/" + PERSISTENT_VOLUME_CLAIM_NAME, HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML)).thenReturn(YAML_STRING);
        when(commonService.setResultObject(gResultMap, PersistentVolumeClaims.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        //call method
        PersistentVolumeClaims result = persistentVolumeClaimsService.getPersistentVolumeClaimsYaml(NAMESPACE, PERSISTENT_VOLUME_CLAIM_NAME, gResultMap);

        //compare result
        assertThat(result).isNotNull();
        assertEquals(YAML_STRING, result.getSourceTypeYaml());
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result.getResultCode());

    }
}
