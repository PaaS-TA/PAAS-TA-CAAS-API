package org.paasta.caas.api.authenticate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AuthenticateServiceTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String USER_NAME = "test-userName";
    private static final String YML = "test-yml";
    private static final String ACCEPT_TYPE = "test-acceptType";
    private static final String CONTENT_TYPE = "test-contentType";

    private static HashMap resultMap = null;
    private static String resultString = "";

    @Mock
    RestTemplateService restTemplateService;
    @Mock
    CommonService commonService;
    @InjectMocks
    AuthenticateService authenticateService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_NAMESPACES+"/"+NAMESPACE+"/serviceaccounts", HttpMethod.POST, YML, Map.class, ACCEPT_TYPE, CONTENT_TYPE)).thenReturn(resultMap);

        authenticateService.createUser(NAMESPACE, YML);
    }

    @Test
    public void testCreateRole() {
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.APIS_URL_NAMESPACES+"/"+NAMESPACE+"/roles", HttpMethod.POST, YML, Map.class, ACCEPT_TYPE, CONTENT_TYPE)).thenReturn(resultMap);

        authenticateService.createRole(NAMESPACE, YML);
    }

    @Test
    public void testCreateRoleBinding() {
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.APIS_URL_NAMESPACES+"/"+NAMESPACE+"/rolebindings", HttpMethod.POST, YML, Map.class, ACCEPT_TYPE, CONTENT_TYPE)).thenReturn(resultMap);

        authenticateService.createRoleBinding(NAMESPACE, YML);
    }

    @Test
    public void testGetToken() {
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, Constants.API_URL_NAMESPACES+"/"+NAMESPACE+"/serviceaccounts/"+USER_NAME, HttpMethod.GET, null, String.class, ACCEPT_TYPE, CONTENT_TYPE)).thenReturn(resultString);

        String result = authenticateService.getToken(NAMESPACE, YML);

//        assertThat(result).isNotNull();
        assertEquals(null, result);
    }
}