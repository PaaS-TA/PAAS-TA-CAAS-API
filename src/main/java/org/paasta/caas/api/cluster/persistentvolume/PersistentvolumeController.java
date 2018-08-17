package org.paasta.caas.api.cluster.persistentvolume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Replica Set 관련 API 를 호출 받는 컨트롤러이다.
 *
 * @author 최윤석
 * @author
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
@RestController
@RequestMapping("/cluster")
public class PersistentvolumeController {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ClusterController.class);
    private final PersistentvolumeService persistentvolumeService;

    @Autowired
    public PersistentvolumeController(PersistentvolumeService persistentvolumeService) {
        this.persistentvolumeService = persistentvolumeService;
    }

    /**
     * PersistentVolume 객체의 리스트를 조회한다.
     *
     * @param map RequestParameter
     * @return ReplicaSetList#
     * @see PersistentvolumeService#getPersistentvolumeList
     */
    @GetMapping(value = "/persistentvolumes")
    public PersistentvolumeList getPersistentvolumeList(@RequestParam Map<String, Object> map){
        return persistentvolumeService.getPersistentvolumeList();
    }

    /**
     * PersistentVolume 객체를 조회한다.
     * @param pvName PersistentVolume Name
     * @param map Request Parameter
     * @return ReplicaSet
     * @see PersistentvolumeService#getPersistentvolume
     */
    @GetMapping(value = "/persistentvolumes/{pvName}")
    public Persistentvolume getPersistentvolume(@PathVariable("pvName") String pvName, @RequestParam Map<String, Object> map) {
        return persistentvolumeService.getPersistentvolume(pvName);
    }

}