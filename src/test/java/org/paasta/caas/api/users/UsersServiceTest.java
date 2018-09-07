package org.paasta.caas.api.users;

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
public class UsersServiceTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String CAAS_ACCOUNT_NAME = "test-caas-account-name";
    private static final String DELETE_URL = "test-delete-url";

    private static HashMap gResultMap = null;
    private static Users gResultModel = null;
    private static Users gFinalResultModel = null;

    @Mock
    RestTemplateService restTemplateService;

    @Mock
    PropertyService propertyService;

    @Mock
    CommonService commonService;

    @InjectMocks
    UsersService usersService;

    @Before
    public void setUp() {
        gResultMap = new HashMap();
        gResultModel = new Users();
        gFinalResultModel = new Users();
        gFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);
    }

    @Test
    public void deleteServiceAccount(){
        // CONDITION
        when(propertyService.getCaasMasterApiListUsersDeleteUrl()).thenReturn(DELETE_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,DELETE_URL.replace("{namespace}", NAMESPACE).replace("{name}", CAAS_ACCOUNT_NAME), HttpMethod.DELETE, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, Users.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        // TEST
        Users resultModel = usersService.deleteServiceAccount(NAMESPACE, CAAS_ACCOUNT_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }
}
