package org.paasta.caas.api.clusters.namespaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Namespaces API 를 호출 받는 컨트롤러.
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

    @Autowired
    public NamespacesController(NamespacesService namespacesService) {
        this.namespacesService = namespacesService;
    }

    @GetMapping("/namespaces/{namespace}")
    public Namespaces getNamespaces(@PathVariable("namespace") String namespace) {
        return namespacesService.getNamespaces(namespace);
    }

    @GetMapping("/namespaces/{namespace}/getResourceQuotaList")
    public ResourceQuotaList getResourceQuotaList(@PathVariable("namespace") String namespace) {
        return namespacesService.getResourceQuotaList(namespace);
    }

}