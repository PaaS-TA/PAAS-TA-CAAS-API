package org.paasta.caas.api.workloads.deployments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Deployments Controller 클래스
 *
 * @author PHR
 * @version 1.0
 * @since 2018.08.13
 */
@RestController
@RequestMapping("/namespaces/{namespace:.+}/deployments")
public class DeploymentsController {

    private final DeploymentsService deploymentsService;

    /**
     * Instantiates a new deployments controller.
     *
     * @param deploymentsService the deployments service
     */
    @Autowired
    public DeploymentsController(DeploymentsService deploymentsService) {
        this.deploymentsService = deploymentsService;
    }

    /**
     * Deployments 목록을 조회한다.
     *
     * @param namespace the namespace
     * @return the deployments list
     */
    @GetMapping
    public DeploymentsList getDeploymentsList(@PathVariable(value = "namespace") String namespace) {
        return deploymentsService.getDeploymentsList(namespace);
    }

    /**
     * Deployments 상세정보를 조회한다.
     *
     * @param namespace       the namespace
     * @param deploymentName the deploymentName name
     * @return the deployments
     */
    @GetMapping(value = "/{deploymentName:.+}")
    public Deployments getDeployments(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "deploymentName") String deploymentName) {
        return deploymentsService.getDeployments(namespace, deploymentName);
    }

    /**
     * Deployments YAML을 조회한다.
     *
     * @param namespace       the namespace
     * @param deploymentName the deploymentName name
     * @return the deployments yaml
     */
    @GetMapping(value = "/{deploymentName:.+}/yaml")
    public Deployments getDeploymentsYaml(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "deploymentName") String deploymentName) {
        return deploymentsService.getDeploymentsYaml(namespace, deploymentName, new HashMap<>());
    }

}
