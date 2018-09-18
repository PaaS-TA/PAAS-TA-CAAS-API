package org.paasta.caas.api.nodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nodes Controller 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@RestController
@RequestMapping(value = "/nodes")
public class NodesController {
    private final NodesService nodesService;

    /**
     * Instantiates a new Nodes controller.
     *
     * @param nodesService the node service
     */
    @Autowired
    public NodesController(NodesService nodesService) {
        this.nodesService = nodesService;
    }

    /**
     * Node 목록을 조회한다.
     *
     * @return the node list
     */
    @GetMapping
    public NodesList getNodeList() {
        return nodesService.getNodeList();
    }


    /**
     * Node를 조회한다.
     *
     * @param nodeName the node name
     * @return the node
     */
    @GetMapping(value = "/{nodeName:.+}")
    public Nodes getNode(@PathVariable(value = "nodeName") String nodeName) {
        return nodesService.getNode(nodeName);
    }
}
