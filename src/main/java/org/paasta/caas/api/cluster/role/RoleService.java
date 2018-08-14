package org.paasta.caas.api.cluster.role;

import com.google.gson.Gson;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.paasta.caas.api.workload.replicaSet.ReplicasetList;
import org.paasta.caas.api.workload.replicaSet.ReplicasetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@Service
public class RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplicasetService.class);
    private final RestTemplateService restTemplateService;

    @Autowired
    public RoleService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @Autowired
    PropertyService propertyService;


    public RoleList getRoleList(String namespace) {
        String apiUrl = propertyService.getCaasMasterApiListRoleListUrl()
                .replaceAll("\\{" + "namespace" + "\\}", namespace);

        HashMap hashMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, apiUrl, HttpMethod.GET, null, Map.class);
        LOGGER.info("########## getRoleList() :: hashMap.toString() :: {}", hashMap.toString());

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(hashMap), RoleList.class);
    }
}
