package org.paasta.caas.api.workloads.pods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Pods Controller 클래스
 *
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
     * Pod 목록을 조회한다.
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
     * Selector를 이용해 Pod 목록을 조회한다. (특정 네임스페이스)
     *
     * @param namespace the namespace
     * @param selector  the selector
     * @return the pod list
     */
    @GetMapping(value = "/resources/{selector:.+}")
    @ResponseBody
    public PodsList getPodListBySelector(@PathVariable(value = "namespace") String namespace,
                                                @PathVariable(value = "selector") String selector) {
        return podsService.getPodListWithLabelSelector(namespace, selector);
    }

    /**
     * Node 이름을 이용해 Pod 목록를 조회한다. (특정 네임스페이스)
     *
     * @param namespace the namespace
     * @param nodeName  the node name
     * @return the pod list
     */
    @GetMapping(value = "/nodes/{nodeName:.+}")
    public PodsList getPodListByNode(@PathVariable(value = "namespace") String namespace,
                                     @PathVariable(value = "nodeName") String nodeName) {
        return podsService.getPodListByNode(namespace, nodeName);
    }

    /**
     * Pod를 조회한다.
     *
     * @param namespace the namespace
     * @param podName   the pod's name
     * @return the pod
     */
    @GetMapping(value = "/{podName:.+}")
    public Pods getPod(@PathVariable(value = "namespace") String namespace,
                       @PathVariable(value = "podName") String podName) {
        return podsService.getPod(namespace, podName);
    }

    /**
     * Pod의 YAML을 조회한다.
     *
     * @param namespace the namespace
     * @param podName   the pod's name
     * @return the pods
     */
    @GetMapping(value = "/{podName:.+}/yaml")
    public Pods getPodYaml(@PathVariable(value = "namespace") String namespace,
                           @PathVariable(value = "podName") String podName) {
        return podsService.getPodYaml(namespace, podName);
    }
}
