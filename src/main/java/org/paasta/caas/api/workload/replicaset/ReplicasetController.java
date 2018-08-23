package org.paasta.caas.api.workload.replicaset;

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
@RequestMapping("/workload")
public class ReplicasetController {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ClusterController.class);
    private final ReplicasetService replicasetService;

    @Autowired
    public ReplicasetController(ReplicasetService replicasetService) {
        this.replicasetService = replicasetService;
    }

    /*
    //ReplicaSet 객체의 리스트를 조회한다.(전체 네임스페이스에서 조회)
    @GetMapping(value = "/replicasets")
    @ResponseBody
    public ReplicasetList getReplicaSetListByAllNamespace(@RequestParam Map<String, Object> map){
        return replicasetService.getReplicaSetListByAllNamespace();
    }
    */

    /**
     * ReplicaSet 객체의 리스트를 조회한다.
     *
     * @param namespace 조회 대상 네임스페이스
     * @param map RequestParameter
     * @return ReplicaSetList#
     * @see ReplicasetService#getReplicasetList
     */
    @GetMapping(value = "/namespaces/{namespace}/replicasets")
    public ReplicasetList getReplicasetList(@PathVariable("namespace") String namespace, @RequestParam Map<String, Object> map){
        return replicasetService.getReplicasetList(namespace);
    }

    /**
     * ReplicaSet 객체를 조회한다.
     * @param namespace namespace
     * @param replicasetsName replicasets Name
     * @param map Request Parameter
     * @return ReplicaSet
     * @see ReplicasetService#getReplicaset
     */
    @GetMapping(value = "/namespaces/{namespace}/replicasets/{replicasetsName}")
    public Replicaset getReplicaset(@PathVariable("namespace") String namespace, @PathVariable("replicasetsName") String replicasetsName, @RequestParam Map<String, Object> map) {
        return replicasetService.getReplicaset(namespace, replicasetsName);
    }

    /**
     * ReplicaSet 객체를 label Selector를 써서 조회한다.
     * @param namespace namespace
     * @param selector
     * @return ReplicasetList
     * @see ReplicasetService#getReplicaset
     */
    @GetMapping(value = "/namespaces/{namespace}/replicasets/resource/{selector}")
    public ReplicasetList getReplicasetLabeSelector(@PathVariable("namespace") String namespace, @PathVariable("selector") String selectors ) {
        return replicasetService.getReplicasetListLabelSelector(namespace, selectors);
    }


}