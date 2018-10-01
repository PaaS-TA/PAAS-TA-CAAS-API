package org.paasta.caas.api.roles;

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
public class RolesServiceTest {

    private static final String TEST_URL = "test-url";
    private static final String NAMESPACE = "test-namespace";
    private static final String ROLE_NAME = "test-role-name";
    private static final String TEST_YML = "test-yml";
    private static HashMap gResultMap = null;

    @Mock
    RestTemplateService restTemplateService;

    @Mock
    CommonService commonService;

    @Mock
    PropertyService propertyService;

    @InjectMocks
    RolesService rolesService;

    @Before
    public void setUp() {

        gResultMap = new HashMap();
    }

    @Test
    public void deleteRole_Valid_ReturnModel() {

        when(propertyService.getCaasMasterApiListRolesDeleteUrl()).thenReturn(TEST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, TEST_URL, HttpMethod.DELETE, null, Map.class)).thenReturn(gResultMap);
        String resultStr = rolesService.deleteRole(NAMESPACE, ROLE_NAME);

        assertThat(resultStr).isNotNull();
        assertEquals(resultStr, Constants.RESULT_STATUS_SUCCESS);
    }

    @Test
    public void deleteRole_FAIL_ReturnModel() {

        when(propertyService.getCaasMasterApiListRolesDeleteUrl()).thenReturn(TEST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, TEST_URL, HttpMethod.DELETE, null, Map.class)).thenReturn(null);
        String resultStr = rolesService.deleteRole(NAMESPACE, ROLE_NAME);

        assertThat(resultStr).isNotNull();
        assertEquals(resultStr, Constants.RESULT_STATUS_FAIL);
    }

    @Test
    public void updateRole_Valid_ReturnModel() {
        when(propertyService.getCaasMasterApiListRolesUpdateUrl()).thenReturn(TEST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, TEST_URL, HttpMethod.PUT, TEST_YML, String.class, Constants.ACCEPT_TYPE_YAML, Constants.ACCEPT_TYPE_YAML)).thenReturn("result");
        String resultStr = rolesService.updateRole(NAMESPACE, ROLE_NAME, TEST_YML);

        assertThat(resultStr).isNotNull();
        assertEquals(resultStr, Constants.RESULT_STATUS_SUCCESS);
    }

    @Test
    public void updateRole_Fail_ReturnModel() {
        when(propertyService.getCaasMasterApiListRolesUpdateUrl()).thenReturn(TEST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, TEST_URL, HttpMethod.PUT, TEST_YML, String.class, Constants.ACCEPT_TYPE_YAML, Constants.ACCEPT_TYPE_YAML)).thenReturn(null);
        String resultStr = rolesService.updateRole(NAMESPACE, ROLE_NAME, TEST_YML);

        assertThat(resultStr).isNotNull();
        assertEquals(resultStr, Constants.RESULT_STATUS_FAIL);
    }
}
