package org.paasta.caas.api.workload.replicaSet;

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
    public ReplicasetList getReplicaSetListByAllNameppace(@RequestParam Map<String, Object> map){
        return replicasetService.getReplicaSetListByAllNamespace();
    }
    */

    /**
     * ReplicaSet 객체의 리스트를 조회한다.
     *
     * @param namespace 조회 대상 네임스페이스
     * @param map RequestParameter
     * @return ReplicaSetList
     * @see ReplicasetService#getReplicaSetList
     */
    @GetMapping(value = "/namespaces/{namespace}/replicasets")
    @ResponseBody
    public ReplicasetList getReplicaSetList(@PathVariable("namespace") String namespace, @RequestParam Map<String, Object> map){
        return replicasetService.getReplicaSetList(namespace);
    }

    /**
     * ReplicaSet 객체를 조회한다.
     * @param namespace namespace
     * @param replicasetsName replicasets Name
     * @param map Request Parameter
     * @return ReplicaSet
     * @see ReplicasetService#getReplicaSetList
     */
    @GetMapping(value = "/namespaces/{namespace}/replicasets/{replicasetsName}")
    @ResponseBody
    public Replicaset getReplicaSet(@PathVariable("namespace") String namespace, @PathVariable("replicasetsName") String replicasetsName, @RequestParam Map<String, Object> map) {
        return replicasetService.getReplicaSet(namespace, replicasetsName);
    }

}