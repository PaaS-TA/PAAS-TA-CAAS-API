package org.paasta.caas.api.workload.pod;

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
@RequestMapping( "/{namespace:.+}/workload/pods" )
public class PodController {
    private final PodService podService;

    /**
     * Instantiates a new Pod controller.
     *
     * @param podService the pod service
     */
    @Autowired
    public PodController ( PodService podService ) {
        this.podService = podService;
    }

    /**
     * Gets pod list.
     *
     * @param namespace the namespace
     * @return the pod list
     */
    @GetMapping
    @ResponseBody
    public PodList getPodList ( @PathVariable( "namespace" ) String namespace,
                                @RequestParam( value = "selector", required = false) String selector ) {
        if ( null != selector )
            return podService.getPodListWithLabelSelector( namespace, selector );
        else
            return podService.getPodList( namespace );
    }

    @GetMapping( "/nodes/{nodeName:.+}" )
    public PodList getPodListByNode ( @PathVariable String namespace, @PathVariable String nodeName ) {
        return podService.getPodListByNode( namespace, nodeName, false );
    }

    @GetMapping( value = "/{podName:.+}" )
    public Pod getPod ( @PathVariable String namespace, @PathVariable String podName ) {
        return podService.getPod( namespace, podName );
    }
}