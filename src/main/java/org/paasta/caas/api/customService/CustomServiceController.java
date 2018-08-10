package org.paasta.caas.api.customService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Custom Service Controller 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.09
 */
@RestController
@RequestMapping("/{namespace:.+}/services")
public class CustomServiceController {

    private final CustomServiceService customServiceService;

    /**
     * Instantiates a new Custom service controller.
     *
     * @param customServiceService the custom service service
     */
    @Autowired
    public CustomServiceController(CustomServiceService customServiceService) {this.customServiceService = customServiceService;}

    /**
     * Gets custom service list.
     *
     * @param namespace the namespace
     * @return the custom service list
     */
    @GetMapping
    public CustomServiceList getCustomServiceList(@PathVariable("namespace") String namespace) {
        return customServiceService.getCustomServiceList(namespace);
    }

    /**
     * Gets custom service.
     *
     * @param namespace   the namespace
     * @param serviceName the service name
     * @return the custom service
     */
    @GetMapping(value = "/{serviceName:.+}")
    public CustomService getCustomService(@PathVariable("namespace") String namespace, @PathVariable("serviceName") String serviceName) {
        return customServiceService.getCustomService(namespace, serviceName);
    }
}
