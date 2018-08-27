package org.paasta.caas.api.workloads.replicaSets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Replica Set 관련 API 를 호출 받는 컨트롤러이다.
 *
 * @author 최윤석
 * @author
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
@RestController
@RequestMapping("/workloads/namespaces/{namespace}/replicasets")
public class ReplicaSetsController {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ClusterController.class);
    private final ReplicaSetsService replicaSetsService;

    @Autowired
    public ReplicaSetsController(ReplicaSetsService replicaSetsService) {
        this.replicaSetsService = replicaSetsService;
    }

    /**
     * ReplicaSet 객체의 리스트를 조회한다.
     *
     * @param namespace 조회 대상 네임스페이스
     * @param map RequestParameter
     * @return ReplicaSetList#
     * @see ReplicaSetsService#getReplicasetList
     */
    @GetMapping
    public ReplicaSetsList getReplicasetList(@PathVariable("namespace") String namespace, @RequestParam Map<String, Object> map){
        return replicaSetsService.getReplicasetList(namespace);
    }

    /**
     * ReplicaSet 객체를 조회한다.
     * @param namespace namespace
     * @param replicasetsName replicasets Name
     * @param map Request Parameter
     * @return ReplicaSet
     * @see ReplicaSetsService#getReplicaset
     */
    @GetMapping(value = "/{replicasetsName}")
    public ReplicaSets getReplicaset(@PathVariable("namespace") String namespace, @PathVariable("replicasetsName") String replicasetsName, @RequestParam Map<String, Object> map) {
        return replicaSetsService.getReplicaset(namespace, replicasetsName);
    }

    /**
     * ReplicaSet 객체를 label Selector를 써서 조회한다.
     * @param namespace namespace
     * @param selector
     * @return ReplicaSetsList
     * @see ReplicaSetsService#getReplicaset
     */
    @GetMapping(value = "/namespaces/{namespace}/replicasets/resource/{selector}")
    public ReplicaSetsList getReplicasetLabeSelector(@PathVariable("namespace") String namespace, @PathVariable("selector") String selectors ) {
        return replicaSetsService.getReplicasetListLabelSelector(namespace, selectors);
    }


}