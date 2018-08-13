package org.paasta.caas.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint Controller 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@RestController
@RequestMapping("/{namespace:.+}/endpoints")
public class EndpointController {

    private final EndpointService endpointService;

    /**
     * Instantiates a new Endpoint controller.
     *
     * @param endpointService the endpoint service
     */
    @Autowired
    public EndpointController(EndpointService endpointService) {this.endpointService = endpointService;}

    /**
     * Gets endpoint list.
     *
     * @param namespace the namespace
     * @return the endpoint list
     */
    @GetMapping
    public EndpointList getEndpointList(@PathVariable("namespace") String namespace) {
        return endpointService.getEndpointList(namespace);
    }


    /**
     * Gets endpoint.
     *
     * @param namespace   the namespace
     * @param serviceName the service name
     * @return the endpoint
     */
    @GetMapping(value = "/{serviceName:.+}")
    public Endpoint getEndpoint(@PathVariable("namespace") String namespace, @PathVariable("serviceName") String serviceName) {
        return endpointService.getEndpoint(namespace, serviceName);
    }
}
