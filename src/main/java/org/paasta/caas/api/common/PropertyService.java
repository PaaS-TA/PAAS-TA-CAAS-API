package org.paasta.caas.api.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Property Service 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.07
 */
@Service
@Data
public class PropertyService {

    @Value("${caasMaster.api.url}")
    private String caasMasterApiUrl;

    @Value("${caasMaster.api.token}")
    private String caasMasterApiToken;

    @Value("${commonApi.url}")
    private String commonApiUrl;

    @Value("${caasMaster.api.list.services.list}")
    private String caasMasterApiListServicesListUrl;

    @Value("${caasMaster.api.list.services.get}")
    private String caasMasterApiListServicesGetUrl;

    @Value("${caasMaster.api.list.endpoints.get}")
    private String caasMasterApiListEndpointsGetUrl;

    // ReplicaSet List
    @Value("${caasMaster.api.list.replicaset.list}")
    private String caasMasterApiListReplicasetListUrl;

    // ReplicaSet
    @Value("${caasMaster.api.list.replicaset.get}")
    private String caasMasterApiListReplicasetGetUrl;

}
