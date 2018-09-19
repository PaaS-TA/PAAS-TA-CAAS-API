package org.paasta.caas.api.workloads.deployments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Deployments Controller 클래스
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
     * @param namespace the namespace
     * @return the deployments list
     */
    @GetMapping
    public DeploymentsList getDeploymentList(@PathVariable(value = "namespace") String namespace) {
        return deploymentsService.getDeploymentList(namespace);
    }

    /**
     * Deployments 객체를 조회한다. (지정한 네임스페이스에 있는 Deployments를 조회한다)
     *
     * @param namespace      the namespace
     * @param deploymentsName the deploymentsName name
     * @return the deployments
     */
    @GetMapping(value = "/{deploymentsName:.+}")
    public Deployments getDeployment(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "deploymentsName") String deploymentsName) {
        return deploymentsService.getDeployment(namespace, deploymentsName);
    }

    /**
     * Deployments YAML을 조회한다.
     *
     * @param namespace   the namespace
     * @param deploymentsName the deploymentsName name
     * @return the deployments yaml
     */
    @GetMapping(value = "/{deploymentsName:.+}/yaml")
    public Deployments getDeploymentsYaml(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "deploymentsName") String deploymentsName) {
        return deploymentsService.getDeploymentsYaml(namespace, deploymentsName, new HashMap<>());
    }

    /**
     * Deployments 객체를 label Selector를 써서 조회한다.
     *
     * @param namespace the namespace
     * @param selector  the selector
     * @return the deployments list
     */
    @GetMapping(value = "/resource/{selector:.+}")
    public DeploymentsList getDeploymentsListLabelSelector(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "selector") String selector) {
        return deploymentsService.getDeploymentsListLabelSelector(namespace, selector);
    }
}
