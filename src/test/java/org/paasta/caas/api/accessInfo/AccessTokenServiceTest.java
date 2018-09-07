package org.paasta.caas.api.accessInfo;

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
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class AccessTokenServiceTest {
    private static final String NAMESPACE = "test-namespace";
    private static final String ACCESS_TOKEN_NAME = "test-access-token-name";
    private static final String GET_URL = "test-get-url";

    private static HashMap gResultMap = null;
    private static AccessToken gResultModel = null;
    private static AccessToken gFinalResultModel = null;
    private static Map gResultDataMap = null;
    private static Map gResultDataFinalMap = null;

    @Mock
    RestTemplateService restTemplateService;

    @Mock
    PropertyService propertyService;

    @Mock
    CommonService commonService;

    @InjectMocks
    AccessTokenService accessTokenService;

    @Before
    public void setUp() {
        gResultMap = new HashMap();

        gResultModel = new AccessToken();
        gFinalResultModel = new AccessToken();

        gResultDataMap = new HashMap<>();
        gResultDataMap.put("ca.crt", "crt-token=");
        gResultDataMap.put("namespace", "test-namespace");
        gResultDataMap.put("token", "accessToken");


        gResultDataFinalMap = new HashMap<>();
        gResultDataFinalMap.put("data", gResultDataMap);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getSecret_Valid_ReturnModel() {

        String token = "accessToken";
        // CONDITION
        //when(propertyService.getCaasMasterApiListAccessInfoGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/api/v1/namespaces" + NAMESPACE + "/secrets/" + ACCESS_TOKEN_NAME, HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(gResultMap.get("data")).thenReturn(gResultDataMap);
        when(gResultDataMap.get("token").toString()).thenReturn(token);

        // TEST
        AccessToken resultModel = accessTokenService.getSecret(NAMESPACE, ACCESS_TOKEN_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();

        //assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }

}
