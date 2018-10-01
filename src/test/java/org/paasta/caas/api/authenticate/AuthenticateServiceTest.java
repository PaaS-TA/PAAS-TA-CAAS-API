package org.paasta.caas.api.authenticate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.yml")
public class AuthenticateServiceTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String USER_NAME = "test-userName";
    private static final String YML = "test-yml";
    private static final String ACCEPT_TYPE = "test-acceptType";
    private static final String CONTENT_TYPE = "test-contentType";
    private static final String GET_URL = "test-get-url";

    private static HashMap resultMap = null;
    private static String resultString = "";

    @Mock
    RestTemplateService restTemplateService;
    @Mock
    PropertyService propertyService;
    @InjectMocks
    AuthenticateService authenticateService;

    @Before
    public void setUp() {
        resultMap = new HashMap();
    }

    @Test
    public void testCreateUser() {
        when(propertyService.getCaasMasterApiListUsersCreateUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE), HttpMethod.POST, YML, Map.class, ACCEPT_TYPE, CONTENT_TYPE)).thenReturn(resultMap);

        authenticateService.createUser(NAMESPACE, YML);
    }

    @Test
    public void testCreateRole() {
        when(propertyService.getCaasMasterApiListRolesCreateUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE), HttpMethod.POST, YML, Map.class, ACCEPT_TYPE, CONTENT_TYPE)).thenReturn(resultMap);

        authenticateService.createRole(NAMESPACE, YML);
    }

    @Test
    public void testCreateRoleBinding() {
        when(propertyService.getCaasMasterApiListRoleBindingsCreateUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE), HttpMethod.POST, YML, Map.class, ACCEPT_TYPE, CONTENT_TYPE)).thenReturn(resultMap);

        authenticateService.createRoleBinding(NAMESPACE, YML);
    }

    @Test
    public void testGetToken() {
        when(propertyService.getCaasMasterApiListUsersGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", USER_NAME), HttpMethod.GET, null, String.class, ACCEPT_TYPE, CONTENT_TYPE)).thenReturn(resultString);

        String result = authenticateService.getToken(NAMESPACE, YML);

        assertEquals(null, result);
    }
}