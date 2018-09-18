package org.paasta.caas.api.customServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Custom Services Controller 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.09
 */
@RestController
@RequestMapping("/namespaces/{namespace:.+}/services")
public class CustomServicesController {

    private final CustomServicesService customServicesService;

    /**
     * Instantiates a new Custom services controller.
     *
     * @param customServicesService the custom services service
     */
    @Autowired
    public CustomServicesController(CustomServicesService customServicesService) {this.customServicesService = customServicesService;}


    /**
     * Services 목록을 조회한다.
     *
     * @param namespace the namespace
     * @return the custom services list
     */
    @GetMapping
    public CustomServicesList getCustomServicesList(@PathVariable(value = "namespace") String namespace) {
        return customServicesService.getCustomServicesList(namespace);
    }


    /**
     * Services 상세 정보를 조회한다.
     *
     * @param namespace   the namespace
     * @param serviceName the service name
     * @return the custom services
     */
    @GetMapping(value = "/{serviceName:.+}")
    public CustomServices getCustomServices(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "serviceName") String serviceName) {
        return customServicesService.getCustomServices(namespace, serviceName);
    }


    /**
     * Services YAML을 조회한다.
     *
     * @param namespace   the namespace
     * @param serviceName the service name
     * @return the custom services yaml
     */
    @GetMapping(value = "/{serviceName:.+}/yaml")
    public CustomServices getCustomServicesYaml(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "serviceName") String serviceName) {
        return customServicesService.getCustomServicesYaml(namespace, serviceName, new HashMap<>());
    }


    /**
     * Services 목록을 조회한다. (Label Selector)
     *
     * @param namespace namespace
     * @param selectors selectors
     * @return the custom services list
     */
    @GetMapping(value = "/resource/{selector:.+}")
    public CustomServicesList getCustomServicesListLabeSelector(@PathVariable("namespace") String namespace, @PathVariable("selector") String selectors) {
        return customServicesService.getCustomServicesListLabelSelector(namespace, selectors);
    }

}
