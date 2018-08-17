package org.paasta.caas.api.workload.deployment;

import com.google.gson.Gson;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Deployment 관련 API에 대해 CaaS API를 향해 호출하고, 결과값을 처리하는 서비스이다.
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Service
public class DeploymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger( DeploymentService.class );
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;
    private final Gson gson;

    @Autowired
    public DeploymentService(RestTemplateService restTemplateService, CommonService commonService,
                             PropertyService propertyService, Gson gson) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
        this.gson = gson;
    }

    /**
     * Deployment List 조회 (전체 네임스페이스 대상)
     *
     * @return DeploymentList
     */
    public DeploymentList getDeploymentListByAllNamespace () {
        final String requestPath = propertyService.getCaasMasterApiListDeploymentAllList();
        HashMap<String, Object> responseMap = ( HashMap<String, Object> ) restTemplateService.send(
            Constants.TARGET_CAAS_MASTER_API, requestPath, HttpMethod.GET, null, Map.class );
        LOGGER.info("#### getDeploymentList() :: hashMap.toString() :: {}", responseMap.toString());

        //DeploymentList deploymentList = gson.fromJson( gson.toJson( responseMap ), DeploymentList.class );
        //return deploymentList;

        return (DeploymentList) commonService.setResultModel(
            gson.fromJson(gson.toJson(responseMap), DeploymentList.class),
            Constants.RESULT_STATUS_SUCCESS, "");
    }

    /**
     * Deployment List 조회 (특정 네임스페이스 대상)
     *
     * @param namespace namespace
     * @param params Request parameters
     * @return DeploymentList
     */
    public DeploymentList getDeploymentList (String namespace, Map<String, Object> params) {
        String requestPath = propertyService.getCaasMasterApiListDeploymentList().replace( "{namespace}", namespace );
        HashMap<String, Object> responseMap = ( HashMap<String, Object> ) restTemplateService.send(
            Constants.TARGET_CAAS_MASTER_API, requestPath, HttpMethod.GET, null, Map.class );
        LOGGER.info( "#### getDeploymentList({}) :: hashMap.toString() :: {}", namespace, responseMap.toString() );

        //DeploymentList deploymentList = gson.fromJson( gson.toJson( responseMap ), DeploymentList.class );
        //return deploymentList;
        return (DeploymentList) commonService.setResultModel(
            gson.fromJson(gson.toJson(responseMap), DeploymentList.class),
            Constants.RESULT_STATUS_SUCCESS, "");
    }

    /**
     * Deployment 조회 (특정 네임스페이스에 있는 deployment)
     *
     * @param namespace namespace
     * @param deploymentName deployment name
     * @param params request parameters
     * @return Deployment
     */
    public Deployment getDeployment (String namespace, String deploymentName, Map<String, Object> params) {
        String requestPath = propertyService.getCaasMasterApiListDeploymentGet()
            .replace( "{namespace}", namespace ).replace( "{deploymentName}", deploymentName );
        HashMap<String, Object> responseMap = ( HashMap<String, Object> ) restTemplateService.send(
            Constants.TARGET_CAAS_MASTER_API, requestPath, HttpMethod.GET, null, Map.class );
        LOGGER.info( "#### getDeployment,({}, {}) :: hashMap.toString() :: {}",
            namespace, deploymentName, responseMap.toString() );

        //Deployment deployment = gson.fromJson( gson.toJson( responseMap ), Deployment.class );
        //return deployment;
        return (Deployment) commonService.setResultModel(
            gson.fromJson(gson.toJson(responseMap), Deployment.class),
            Constants.RESULT_STATUS_SUCCESS, "");
    }
}
