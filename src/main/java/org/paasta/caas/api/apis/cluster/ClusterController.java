package org.paasta.caas.api.apis.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 클러스터 API 를 호출 받는 컨트롤러이다.
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
@RestController
@RequestMapping("/cluster")
public class ClusterController {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ClusterController.class);
    private final ClusterService clusterService;

    @Autowired
    public ClusterController(ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    /**
     * description.
     *
     * @param req   HttpServletRequest(자바클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @GetMapping("/namespaces")
    public Map<String, Object> getNamespaceList(HttpServletRequest req) throws Exception {
        return clusterService.getNamespaceList();
    }

}