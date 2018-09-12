package org.paasta.caas.api.clusters.namespaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Namespaces Controller 클래스.
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
@RestController
@RequestMapping("/clusters")
public class NamespacesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NamespacesController.class);

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
     * Gets namespaces.
     *
     * @param namespace the namespaces
     * @return the namespaces
     */
    @GetMapping("/namespaces/{namespace}")
    public Namespaces getNamespaces(@PathVariable("namespace") String namespace) {
        return namespacesService.getNamespaces(namespace);
    }

    /**
     * Gets ResourceQuotaList.
     *
     * @param namespace the namespaces
     * @return the ResourceQuotaList
     */
    @GetMapping("/namespaces/{namespace}/getResourceQuotaList")
    public ResourceQuotaList getResourceQuotaList(@PathVariable("namespace") String namespace) {
        return namespacesService.getResourceQuotaList(namespace);
    }

}