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
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")

public class AccessTokenServiceTest {
    private static final String NAMESPACE = "test-namespace";
    private static final String GET_URL = "test-get-url";
    private static final String ACCESS_TOKEN_NAME = "test-access-token-name";
    private static final String ENCODE_TOKEN = "YWNjZXNzVG9rZW4=";
    private static final String DECODE_TOKEN = "accessToken";

    private static final String ENCODE_CERT_TOKEN = "Y2VydEFjY2Vzc1Rva2Vu";
    private static final String DECODE_CERT_TOKEN = "certAccessToken";

    private static HashMap gResultMap = null;
    private static HashMap gResultDataMap = null;

    private static AccessToken gResultModel = null;
    private static AccessToken gFinalResultModel = null;

    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private PropertyService propertyService;

    @Mock
    private CommonService commonService;

    @InjectMocks
    AccessTokenService accessTokenService;

    @Before
    public void setUp() {

        gResultMap = new HashMap();
        gResultDataMap = new HashMap<>();
        gResultDataMap.put("token", ENCODE_TOKEN);
        gResultDataMap.put("ca.crt", ENCODE_CERT_TOKEN);
        gResultMap.put("data", gResultDataMap);

        gResultModel = new AccessToken();
        gFinalResultModel = new AccessToken();
        gFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void getSecret_Valid_ReturnModel() {

        String token = "accessToken";
        // CONDITION
        when(propertyService.getCaasMasterApiListSecretsGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{accessTokenName}", ACCESS_TOKEN_NAME), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        gResultModel.setCaCertToken(DECODE_CERT_TOKEN);
        gResultModel.setUserAccessToken(DECODE_TOKEN);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        // TEST
        AccessToken resultModel = accessTokenService.getSecret(NAMESPACE, ACCESS_TOKEN_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }

}
