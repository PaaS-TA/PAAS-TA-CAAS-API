package org.paasta.caas.api.workloads.deployments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Deployments 관련 API에 대해 호출을 받는 컨트롤러이다.
 *
 * @author Hyungu Cho
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
     * Deployments 객체의 리스트를 조회한다. (지정한 네임스페이스에서 조회)
     *
     * @param namespace 조회하려는 객체가 속한 namespace
     * @return the deployment list
     */
    @GetMapping
    public DeploymentsList getDeploymentList(@PathVariable(value = "namespace") String namespace) {
        return deploymentsService.getDeploymentList(namespace);
    }

    /**
     * Deployments 객체를 조회한다. (지정한 네임스페이스에 있는 Deployment를 조회)
     *
     * @param namespace      조회하려는 객체가 속한 namespace
     * @param deploymentName 조회하려는 deployment 객체의 이름
     * @return the deployment
     */
    @GetMapping(value = "/{deploymentName:.+}")
    public Deployments getDeployment(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "deploymentName") String deploymentName) {
        return deploymentsService.getDeployment(namespace, deploymentName);
    }

    /**
     * Deployments 객체를 label Selector를 써서 조회한다.
     *
     * @param namespace the namespace
     * @param selector  the selector
     * @return the deployment list
     */
    @GetMapping(value = "/resource/{selector:.+}")
    public DeploymentsList getDeploymentsListLabelSelector(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "selector") String selector) {
        return deploymentsService.getDeploymentsListLabelSelector(namespace, selector);
    }
}
