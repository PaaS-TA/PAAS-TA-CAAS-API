package org.paasta.caas.api.workloads.pods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 클러스터 API 를 호출 받는 컨트롤러이다.
 *
 * @author 최윤석
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.01
 */
@RestController
@RequestMapping(value = "/namespaces/{namespace:.+}/pods")
public class PodsController {
    private final PodsService podsService;

    /**
     * Instantiates a new Pods controller.
     *
     * @param podsService the pod service
     */
    @Autowired
    public PodsController(PodsService podsService) {
        this.podsService = podsService;
    }

    /**
     * Gets pod list.
     *
     * @param namespace the namespace
     * @return the pod list
     */
    @GetMapping
    @ResponseBody
    public PodsList getPodList(@PathVariable(value = "namespace") String namespace) {
        return podsService.getPodList(namespace);
    }

    /**
     * Gets pod list by selector
     *
     * @param namespace the namespace
     * @param selector  the selector
     * @return the pod list
     */
    @GetMapping(value = "/resource/{selector:.+}")
    @ResponseBody
    public PodsList getPodListWithLabelSelector(@PathVariable(value = "namespace") String namespace,
                                                @PathVariable(value = "selector") String selector) {
        return podsService.getPodListWithLabelSelector(namespace, selector);
    }

    /**
     * Gets pod list by node name
     *
     * @param namespace the namespace
     * @param nodeName  the node name
     * @return the pod list
     */
    @GetMapping(value = "/node/{nodeName:.+}")
    public PodsList getPodListByNode(@PathVariable(value = "namespace") String namespace,
                                     @PathVariable(value = "nodeName") String nodeName) {
        return podsService.getPodListByNode(namespace, nodeName, false);
    }

    /**
     * Gets pod by pod name
     *
     * @param namespace the namespace
     * @param podName   the pod name
     * @return the pod
     */
    @GetMapping(value = "/{podName:.+}")
    public Pods getPod(@PathVariable(value = "namespace") String namespace,
                       @PathVariable(value = "podName") String podName) {
        return podsService.getPod(namespace, podName);
    }
}