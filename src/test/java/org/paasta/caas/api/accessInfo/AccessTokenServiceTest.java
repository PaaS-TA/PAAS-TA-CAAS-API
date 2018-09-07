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
    private static final String ACCESS_TOKEN_NAME = "test-access-token-name";
    private static final String ENCODE_TOKEN = "YWNjZXNzVG9rZW4=";
    private static final String DECODE_TOKEN = "accessToken";

    private static HashMap gResultMap = null;
    private static AccessToken gResultModel = null;
    private static AccessToken gFinalResultModel = null;
    private static HashMap gResultDataMap = null;

    @Mock
    RestTemplateService restTemplateService;

    @InjectMocks
    AccessTokenService accessTokenService;

    @Before
    public void setUp() {

        gResultMap = new HashMap();
        gResultDataMap = new HashMap<>();
        gResultDataMap.put("token", ENCODE_TOKEN);
        gResultMap.put("data", gResultDataMap);


        gResultModel = new AccessToken();
        gFinalResultModel = new AccessToken();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void getSecret_Valid_ReturnModel() {

        String token = "accessToken";
        // CONDITION
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/api/v1/namespaces/test-namespace/secrets/test-access-token-name", HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);

        // TEST
        AccessToken resultModel = accessTokenService.getSecret(NAMESPACE, ACCESS_TOKEN_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(resultModel.getUserAccessToken(), DECODE_TOKEN);
    }

}
