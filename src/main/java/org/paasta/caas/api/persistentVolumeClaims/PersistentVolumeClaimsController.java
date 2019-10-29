package org.paasta.caas.api.persistentVolumeClaims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * PersistentVolumeClaims Controller 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-10-24
 */
@RestController
@RequestMapping("/namespaces/{namespace:.+}/persistentVolumeClaims")
public class PersistentVolumeClaimsController {
    private final PersistentVolumeClaimsService persistentVolumeClaimsService;

    /**
     * Instantiates a new persistentVolumeClaims controller.
     *
     * @param persistentVolumeClaimsService the persistentVolumeClaims service
     */
    @Autowired
    public PersistentVolumeClaimsController(PersistentVolumeClaimsService persistentVolumeClaimsService) {
        this.persistentVolumeClaimsService = persistentVolumeClaimsService;
    }

    /**
     * PersistentVolumeClaims 목록을 조회한다.
     *
     * @param namespace the namespace
     * @return the PersistentVolumeClaims List
     */
    @GetMapping
    public PersistentVolumeClaimsList getPersistentVolumeClaimsList(@PathVariable(value = "namespace") String namespace) {
        return persistentVolumeClaimsService.getPersistentVolumeClaimsList(namespace);
    }

    /**
     * PersistentVolumeClaims 상세 정보를 조회한다.
     *
     * @param namespace the namespace
     * @param persistentVolumeClaimName the PersistentVolumeClaims
     * @return the PersistentVolumeClaims
     */
    @GetMapping(value = "/{persistentVolumeClaimName:.+}")
    public PersistentVolumeClaims getPersistentVolumeClaims(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "persistentVolumeClaimName") String persistentVolumeClaimName) {
        return persistentVolumeClaimsService.getPersistentVolumeClaims(namespace, persistentVolumeClaimName);
    }

    /**
     * PersistentVolumeClaims YAML 을 조회한다.
     *
     * @param namespace the namespace
     * @param persistentVolumeClaimName the PersistentVolumeClaims
     * @return the PersistentVolumeClaims
     */
    @GetMapping(value = "/{persistentVolumeClaimName:.+}/yaml")
    public PersistentVolumeClaims getPersistentVolumeClaimsYaml(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "persistentVolumeClaimName") String persistentVolumeClaimName) {
        return persistentVolumeClaimsService.getPersistentVolumeClaimsYaml(namespace, persistentVolumeClaimName, new HashMap<>());
    }
}
