package org.paasta.caas.api.clusters.namespace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/namespaces/{namespace}")
    public Namespace getNamespaces(@PathVariable("namespace") String namespace) {
        return namespaceService.getNamespaces(namespace);
    }

}