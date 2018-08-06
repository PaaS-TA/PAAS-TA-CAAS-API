package org.paasta.caas.api.workload.pod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 클러스터 API 를 호출 받는 컨트롤러이다.
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
@RestController
@RequestMapping("/workload")
public class PodController {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ClusterController.class);
    private final PodService podService;

    @Autowired
    public PodController(PodService podService) {
        this.podService = podService;
    }

    /**
     * description.
     *
     * //@param req   HttpServletRequest(자바클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @GetMapping(value = "/namespaces/{namespace}/pods")
    @ResponseBody
    public Map<String, Object> getPodList(@PathVariable("namespace") String namespace, @RequestParam Map<String, Object> map){
        return podService.getPodList(namespace, map);
    }

}