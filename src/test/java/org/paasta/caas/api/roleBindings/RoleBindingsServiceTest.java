package org.paasta.caas.api.roleBindings;

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

/**
 * The type Role bindings service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class RoleBindingsServiceTest {

    private static final String NAMESPACE = "test-namespace";
    private static final String ROLE_BINDING_NAME = "test-role-binding-name";
    private static final String LIST_URL = "test-list-url";
    private static final String GET_URL = "test-get-url";
    private static final String CREATE_URL = "test-create-url";
    private static final String DELETE_URL = "test-delete-url";
    private static final String UPDATE_URL = "test-update-url";
    private static final String RESULT_MAP_STATUS_SUCCESS = "Success";
    private static final String YML = "test-yml";

    private static HashMap<String, String> gResultMap = null;
    private static RoleBindingsList gResultListModel = null;
    private static RoleBindingsList gFinalResultListModel = null;
    private static RoleBindings gTestModel = null;
    private static RoleBindings gResultModel = null;
    private static RoleBindings gFinalResultModel = null;


    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private CommonService commonService;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private RoleBindingsService roleBindingsService;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        gResultMap = new HashMap<>();
        gResultMap.put("status", RESULT_MAP_STATUS_SUCCESS);

        gResultListModel = new RoleBindingsList();
        gFinalResultListModel = new RoleBindingsList();
        gFinalResultListModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gResultModel = new RoleBindings();
        gFinalResultModel = new RoleBindings();
        gFinalResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gTestModel = new RoleBindings();
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////// MethodName_StateUnderTest_ExpectedBehavior
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Gets role binding list valid return model.
     */
    @Test
    public void getRoleBindingList_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListRoleBindingsListUrl()).thenReturn(LIST_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, LIST_URL
                .replace("{namespace}", NAMESPACE), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, RoleBindingsList.class)).thenReturn(gResultListModel);
        when(commonService.setResultModel(gResultListModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultListModel);

        // TEST
        RoleBindingsList resultModel = roleBindingsService.getRoleBindingList(NAMESPACE);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Gets role binding valid return model.
     */
    @Test
    public void getRoleBinding_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListRoleBindingsGetUrl()).thenReturn(GET_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, GET_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", ROLE_BINDING_NAME), HttpMethod.GET, null, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, RoleBindings.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        // TEST
        RoleBindings resultModel = roleBindingsService.getRoleBinding(NAMESPACE, ROLE_BINDING_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Create role binding valid return model.
     */
    @Test
    public void createRoleBinding_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListRoleBindingsCreateUrl()).thenReturn(CREATE_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, CREATE_URL
                .replace("{namespace}", NAMESPACE), HttpMethod.POST, gTestModel, Map.class)).thenReturn(gResultMap);
        when(commonService.setResultObject(gResultMap, RoleBindings.class)).thenReturn(gResultModel);
        when(commonService.setResultModel(gResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gFinalResultModel);

        // TEST
        RoleBindings resultModel = roleBindingsService.createRoleBinding(NAMESPACE, gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Delete role binding valid return model.
     */
    @Test
    public void deleteRoleBinding_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListRoleBindingsDeleteUrl()).thenReturn(DELETE_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, DELETE_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", ROLE_BINDING_NAME), HttpMethod.DELETE, null, Map.class)).thenReturn(gResultMap);

        // TEST
        String resultString = roleBindingsService.deleteRoleBinding(NAMESPACE, ROLE_BINDING_NAME);

        // VERIFY
        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
    }


    /**
     * Delete role binding un valid return model.
     */
    @Test
    public void deleteRoleBinding_UnValid_ReturnModel() {
        // SET
        gResultMap.put("status", "Fail");

        // CONDITION
        when(propertyService.getCaasMasterApiListRoleBindingsDeleteUrl()).thenReturn(DELETE_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, DELETE_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", ROLE_BINDING_NAME), HttpMethod.DELETE, null, Map.class)).thenReturn(gResultMap);

        // TEST
        String resultString = roleBindingsService.deleteRoleBinding(NAMESPACE, ROLE_BINDING_NAME);

        // VERIFY
        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_FAIL, resultString);
    }


    /**
     * Update role binding valid return model.
     */
    @Test
    public void updateRoleBinding_Valid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListRoleBindingsUpdateUrl()).thenReturn(UPDATE_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, UPDATE_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", ROLE_BINDING_NAME), HttpMethod.PUT, YML, String.class, Constants.ACCEPT_TYPE_YAML, Constants.ACCEPT_TYPE_YAML)).thenReturn("result");

        // TEST
        String resultString = roleBindingsService.updateRoleBinding(NAMESPACE, ROLE_BINDING_NAME, YML);

        // VERIFY
        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
    }


    /**
     * Update role binding un valid return model.
     */
    @Test
    public void updateRoleBinding_UnValid_ReturnModel() {
        // CONDITION
        when(propertyService.getCaasMasterApiListRoleBindingsUpdateUrl()).thenReturn(UPDATE_URL);
        when(restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, UPDATE_URL
                .replace("{namespace}", NAMESPACE)
                .replace("{name}", ROLE_BINDING_NAME), HttpMethod.PUT, YML, String.class, Constants.ACCEPT_TYPE_YAML, Constants.ACCEPT_TYPE_YAML)).thenReturn(null);

        // TEST
        String resultString = roleBindingsService.updateRoleBinding(NAMESPACE, ROLE_BINDING_NAME, YML);

        // VERIFY
        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_FAIL, resultString);
    }

}
