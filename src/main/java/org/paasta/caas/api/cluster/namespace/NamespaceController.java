package org.paasta.caas.api.cluster.namespace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class NamespaceController {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ClusterController.class);
    private final NamespaceService namespaceService;

    @Autowired
    public NamespaceController(NamespaceService namespaceService) {
        this.namespaceService = namespaceService;
    }

    // TODO :: REMOVE
    @GetMapping(value = "/namespaces2")
    public Map<String, Object> getNamespaceList(@RequestParam Map<String, Object> map){
        return namespaceService.getNamespaceList(map);
    }

    @GetMapping("/namespaces")
    public Namespace getNamespaceList() {
        return namespaceService.getNamespaceList();
    }

}