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
@RestController                 // included ResponseBody annotation.
@RequestMapping( "/workload" )
public class DeploymentsController {

    private final DeploymentsService deploymentsService;

    @Autowired
    public DeploymentsController(DeploymentsService deploymentsService) {
        this.deploymentsService = deploymentsService;
    }

    /**
     * Deployments 객체의 리스트를 조회한다. (전체 네임스페이스에서 조회)
     * @param params request parameters
     * @return DeploymentsList
     */
    @GetMapping( "/deployments" )
    public DeploymentsList getDeploymentListByAllNamespace(@RequestParam Map<String, Object> params ) {
        return deploymentsService.getDeploymentListByAllNamespace();
    }

    /**
     * Deployments 객체의 리스트를 조회한다. (지정한 네임스페이스에서 조회)
     * @param namespace 조회하려는 객체가 속한 namespace
     * @param params request parameters
     * @return
     */
    @GetMapping( "/namespaces/{namespace}/deployments" )
    public DeploymentsList getDeploymentList(@PathVariable String namespace, @RequestParam Map<String, Object> params) {
        return deploymentsService.getDeploymentList(namespace, params);
    }

    /**
     * Deployments 객체를 조회한다. (지정한 네임스페이스에 있는 Deployment를 조회)
     * @param namespace 조회하려는 객체가 속한 namespace
     * @param deploymentName 조회하려는 deployment 객체의 이름
     * @param params request parameters
     * @return
     */
    @GetMapping( "/namespaces/{namespace}/deployments/{deploymentName}" )
    public Deployments getDeployment(@PathVariable String namespace, @PathVariable String deploymentName,
                                     @RequestParam Map<String, Object> params) {
        return deploymentsService.getDeployment(namespace, deploymentName, params);
    }
}
