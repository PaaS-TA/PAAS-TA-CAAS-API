package org.paasta.caas.api.workload.pod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 클러스터 API 를 호출 받는 컨트롤러이다.
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
@RestController
// TODO :: REMOVE
//@RequestMapping("/workload")
@RequestMapping("/{namespace:.+}/workload/pods")
public class PodController {
    // TODO :: REMOVE
    //private static final Logger LOGGER = LoggerFactory.getLogger(ClusterController.class);
    private final PodService podService;

    /**
     * Instantiates a new Pod controller.
     *
     * @param podService the pod service
     */
    @Autowired
    public PodController(PodService podService) {
        this.podService = podService;
    }

    // TODO :: REMOVE
//    /**
//     * description.
//     *
//     * //@param req   HttpServletRequest(자바클래스)
//     * @return Map(자바클래스)
//     * @throws Exception Exception(자바클래스)
//     */
//    @GetMapping(value = "/namespaces/{namespace}/pods")
//    @ResponseBody
//    public Map<String, Object> getPodList(@PathVariable("namespace") String namespace, @RequestParam Map<String, Object> map){
//        return podService.getPodList(namespace, map);
//    }

    /**
     * Gets pod list.
     *
     * @param namespace the namespace
     * @return the pod list
     */
    @GetMapping
    @ResponseBody
    public PodList getPodList(@PathVariable("namespace") String namespace) {
        return podService.getPodList(namespace);
    }


    /**
     * Gets pod list.
     *
     * @param namespace the namespace
     * @param selector  the selector
     * @return the pod list
     */
    @GetMapping(value = "/{selector:.+}")
    @ResponseBody
    public PodList getPodList(@PathVariable("namespace") String namespace, @PathVariable("selector") String selector) {
        return podService.getPodList(namespace, selector);
    }

    @GetMapping( value = "/detail/{podName:.+}" )
    public Pod getPod(@PathVariable String namespace, @PathVariable String podName) {
        return podService.getPod(namespace, podName);
    }
}