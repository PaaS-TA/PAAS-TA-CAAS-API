package org.paasta.caas.api.customServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
     * Services를 생성한다.
     *
     * @param namespace       the namespace
     * @param services        the Services
     * @return                 return is succeeded
     */
    @PostMapping
    public Map<?,?> createServicesYaml(@PathVariable(value = "namespace") String namespace, @RequestBody Object services) {
        return customServicesService.createServicesYaml(namespace, services, new HashMap<>());
    }


    /**
     * Services를 제거한다.
     *
     * @param namespace       the namespace
     * @param name             the Services name
     * @return                 return is succeeded
     */
    @DeleteMapping("/{name}")
    public Map<?,?> deleteServicesYaml(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "name") String name) {
        return customServicesService.deleteServicesYaml(namespace, name, new HashMap<>());
    }
}
