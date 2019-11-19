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

    @Value("${commonApi.url}")
    private String commonApiUrl;

    @Value("${caasMaster.api.list.services.list}")
    private String caasMasterApiListServicesListUrl;

    @Value("${caasMaster.api.list.services.get}")
    private String caasMasterApiListServicesGetUrl;

    @Value("${caasMaster.api.list.services.create}")
    private String caasMasterApiListServicesCreate;

    @Value("${caasMaster.api.list.services.delete}")
    private String caasMasterApiListServicesDelete;

    @Value("${caasMaster.api.list.endpoints.list}")
    private String caasMasterApiListEndpointsListUrl;

    @Value("${caasMaster.api.list.endpoints.get}")
    private String caasMasterApiListEndpointsGetUrl;

    @Value("${caasMaster.api.list.pods.list}")
    private String caasMasterApiListPodsListUrl;

    @Value("${caasMaster.api.list.pods.get}")
    private String caasMasterApiListPodsGetUrl;

    @Value("${caasMaster.api.list.nodes.list}")
    private String caasMasterApiListNodesListUrl;

    @Value("${caasMaster.api.list.nodes.get}")
    private String caasMasterApiListNodesGetUrl;

    @Value("${caasMaster.api.list.replicaSets.list}")
    private String caasMasterApiListReplicaSetsListUrl;

    @Value("${caasMaster.api.list.replicaSets.get}")
    private String caasMasterApiListReplicaSetsGetUrl;

    @Value("${caasMaster.api.list.persistentvolumes.list}")
    private String caasMasterApiListPersistentvolumesListUrl;

    @Value("${caasMaster.api.list.persistentvolumes.get}")
    private String caasMasterApiListPersistentvolumesGetUrl;

    @Value("${caasMaster.api.list.persistentVolumeClaims.list}")
    private String caasMasterApiListPersistentVolumeClaimsListUrl;

    @Value("${caasMaster.api.list.persistentVolumeClaims.get}")
    private String caasMasterApiListPersistentVolumeClaimsGetUrl;

    @Value("${caasMaster.api.list.events.list}")
    private String caasMasterApiListEventsListUrl;

    @Value("${caasMaster.api.list.roles.list}")
    private String caasMasterApiListRolesListUrl;

    @Value("${caasMaster.api.list.roles.get}")
    private String caasMasterApiListRolesGetUrl;

    @Value("${caasMaster.api.list.roles.create}")
    private String caasMasterApiListRolesCreateUrl;

    @Value("${caasMaster.api.list.roles.delete}")
    private String caasMasterApiListRolesDeleteUrl;

    @Value("${caasMaster.api.list.roles.update}")
    private String caasMasterApiListRolesUpdateUrl;

    @Value("${caasMaster.api.list.deployments.list}")
    private String caasMasterApiListDeploymentsList;

    @Value("${caasMaster.api.list.deployments.get}")
    private String caasMasterApiListDeploymentsGet;

    @Value("${caasMaster.api.list.deployments.create}")
    private String caasMasterApiListDeploymentsCreate;

    @Value("${caasMaster.api.list.deployments.delete}")
    private String caasMasterApiListDeploymentsDelete;

    @Value("${caasMaster.api.list.roleBindings.list}")
    private String caasMasterApiListRoleBindingsListUrl;

    @Value("${caasMaster.api.list.roleBindings.get}")
    private String caasMasterApiListRoleBindingsGetUrl;

    @Value("${caasMaster.api.list.roleBindings.create}")
    private String caasMasterApiListRoleBindingsCreateUrl;

    @Value("${caasMaster.api.list.roleBindings.delete}")
    private String caasMasterApiListRoleBindingsDeleteUrl;

    @Value("${caasMaster.api.list.roleBindings.update}")
    private String caasMasterApiListRoleBindingsUpdateUrl;

    @Value("${caasMaster.api.list.users.get}")
    private String caasMasterApiListUsersGetUrl;

    @Value("${caasMaster.api.list.users.create}")
    private String caasMasterApiListUsersCreateUrl;

    @Value("${caasMaster.api.list.users.delete}")
    private String caasMasterApiListUsersDeleteUrl;

    @Value("${caasMaster.api.list.namespaces.get}")
    private String caasMasterApiListNamespaceGetUrl;

    @Value("${caasMaster.api.list.namespaces.create}")
    private String caasMasterApiListNamespaceCreateUrl;

    @Value("${caasMaster.api.list.resourceQuotas.list}")
    private String caasMasterApiListResourceQuotasListUrl;

    @Value("${caasMaster.api.list.secrets.get}")
    private String caasMasterApiListSecretsGetUrl;

    @Value("${caasMaster.api.list.secrets.create}")
    private String caasMasterApiListSecretsCreateUrl;
}
