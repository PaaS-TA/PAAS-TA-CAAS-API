package org.paasta.caas.api.clusters.persistentVolumes;

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
@RequestMapping("/clusters/persistentvolumes")
public class PersistentVolumesController {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ClusterController.class);
    private final PersistentVolumesService persistentVolumesService;

    @Autowired
    public PersistentVolumesController(PersistentVolumesService persistentVolumesService) {
        this.persistentVolumesService = persistentVolumesService;
    }

    /**
     * PersistentVolume 객체의 리스트를 조회한다.
     *
     * @param map RequestParameter
     * @return ReplicaSetList#
     * @see PersistentVolumesService#getPersistentvolumeList
     */
    @GetMapping
    public PersistentVolumesList getPersistentvolumeList(@RequestParam Map<String, Object> map){
        return persistentVolumesService.getPersistentvolumeList();
    }

    /**
     * PersistentVolume 객체를 조회한다.
     * @param pvName PersistentVolume Name
     * @param map Request Parameter
     * @return ReplicaSet
     * @see PersistentVolumesService#getPersistentvolume
     */
    @GetMapping(value = "/{pvName}")
    public PersistentVolumes getPersistentvolume(@PathVariable("pvName") String pvName, @RequestParam Map<String, Object> map) {
        return persistentVolumesService.getPersistentvolume(pvName);
    }

}