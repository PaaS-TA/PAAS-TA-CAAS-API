package org.paasta.caas.api.clusters.namespaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Namespaces Controller 클래스.
 *
 * @author indra
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
@RestController
@RequestMapping("/namespaces")
public class NamespacesController {

    private final NamespacesService namespacesService;

    /**
     * Instantiates a Namespaces controller.
     *
     * @param namespacesService the namespaces service
     */
    @Autowired
    public NamespacesController(NamespacesService namespacesService) {
        this.namespacesService = namespacesService;
    }

    /**
     * Namespaces 상세정보를 조회한다.
     *
     * @param namespace the namespaces
     * @return the namespaces
     */
    @GetMapping("/{namespace:.+}")
    public Namespaces getNamespaces(@PathVariable("namespace") String namespace) {
        return namespacesService.getNamespaces(namespace);
    }

    /**
     * ResourceQuota 목록을 조회한다.
     *
     * @param namespace the namespaces
     * @return the ResourceQuotaList
     */
    @GetMapping("/{namespace:.+}/resourceQuotas")
    public ResourceQuotaList getResourceQuotaList(@PathVariable("namespace") String namespace) {
        return namespacesService.getResourceQuotaList(namespace);
    }

    @PostMapping()
    public Map<?,?> createDeploymentsYaml(@RequestBody Object namespace) {
        return namespacesService.createNamespaces(namespace);
    }

}