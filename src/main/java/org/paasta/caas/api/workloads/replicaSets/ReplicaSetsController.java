package org.paasta.caas.api.workloads.replicaSets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Replica Set Controller 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
@RestController
@RequestMapping("/namespaces/{namespace:.+}/replicasets")
public class ReplicaSetsController {

    private final ReplicaSetsService replicaSetsService;

    /**
     * Instantiates a new Replicaset controller.
     *
     * @param replicaSetsService the replicaset service
     */
    @Autowired
    public ReplicaSetsController(ReplicaSetsService replicaSetsService) {
        this.replicaSetsService = replicaSetsService;
    }

    /**
     * ReplicaSet 목록을 조회한다.
     *
     * @param namespace the namespace
     * @return the replicaset list
     */
    @GetMapping
    public ReplicaSetsList getReplicasetList(@PathVariable("namespace") String namespace){
        return replicaSetsService.getReplicasetList(namespace);
    }

    /**
     * ReplicaSet 상세정보를 조회한다.
     *
     * @param namespace the namespace
     * @param replicaSetName the replicasets name
     * @return the replicaset
     */
    @GetMapping(value = "/{replicaSetName:.+}")
    public ReplicaSets getReplicaset(@PathVariable("namespace") String namespace, @PathVariable("replicaSetName") String replicaSetName) {
        return replicaSetsService.getReplicaset(namespace, replicaSetName);
    }

    /**
     * ReplicaSet YAML을 조회한다.
     *
     * @param namespace the namespace
     * @param replicaSetName the service name
     * @return the custom services yaml
     */
    @GetMapping(value = "/{replicaSetName:.+}/yaml")
    public ReplicaSets getCustomServicesYaml(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "replicaSetName") String replicaSetName) {
        return replicaSetsService.getReplicasetYaml(namespace, replicaSetName); // , new HashMap<>()
    }

    /**
     * ReplicaSet 객체를 label Selector를 써서 조회한다.
     * @param namespace namespace
     * @param selectors selectors
     * @return ReplicaSetsList
     * @see ReplicaSetsService#getReplicaset
     */
    @GetMapping(value = "/resource/{selector}")
    public ReplicaSetsList getReplicasetLabeSelector(@PathVariable("namespace") String namespace, @PathVariable("selector") String selectors ) {
        return replicaSetsService.getReplicasetListLabelSelector(namespace, selectors);
    }

}