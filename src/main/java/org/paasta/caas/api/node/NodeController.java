package org.paasta.caas.api.node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Node Controller 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@RestController
@RequestMapping("/nodes")
public class NodeController {

    private final NodeService nodeService;

    /**
     * Instantiates a new Node controller.
     *
     * @param nodeService the node service
     */
    @Autowired
    public NodeController(NodeService nodeService) {this.nodeService = nodeService;}

    /**
     * Gets node list.
     *
     * @return the node list
     */
    @GetMapping
    public NodeList getNodeList() {
        return nodeService.getNodeList();
    }


    /**
     * Gets node.
     *
     * @param nodeName the node name
     * @return the node
     */
    @GetMapping(value = "/{nodeName:.+}")
    public Node getNode(@PathVariable("nodeName") String nodeName) {
        return nodeService.getNode(nodeName);
    }

}
