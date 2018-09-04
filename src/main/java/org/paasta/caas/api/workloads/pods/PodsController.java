package org.paasta.caas.api.workloads.pods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 클러스터 API 를 호출 받는 컨트롤러이다.
 *
 * @author 최윤석
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
@RestController
@RequestMapping( "/workloads/{namespace:.+}/pods" )
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
    public PodsList getPodList (@PathVariable( "namespace" ) String namespace,
                                @RequestParam( value = "selector", required = false) String selector ) {
        if ( null != selector )
            return podsService.getPodListWithLabelSelector( namespace, selector );
        else
            return podsService.getPodList( namespace );
    }

    @GetMapping( "/nodes/{nodeName:.+}" )
    public PodsList getPodListByNode (@PathVariable String namespace, @PathVariable String nodeName ) {
        return podsService.getPodListByNode( namespace, nodeName, false );
    }

    @GetMapping( value = "/{podName:.+}" )
    public Pods getPod (@PathVariable String namespace, @PathVariable String podName ) {
        return podsService.getPod( namespace, podName );
    }
}