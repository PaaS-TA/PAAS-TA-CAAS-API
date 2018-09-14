package org.paasta.caas.api.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Events Controller 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.13
 */
@RestController
@RequestMapping("/namespaces/{namespace:.+}/events")
public class EventsController {

    private final EventsService eventsService;

    /**
     * Instantiates a new event controller.
     *
     * @param eventsService the event service
     */
    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }


    /**
     * Gets Events list.
     *
     * @param namespace    the namespace
     * @param resourceName the resourceName
     * @return the event list
     */
    @GetMapping(value = "/resource/{resourceName:.+}")
    public EventsList getEventList(@PathVariable("namespace") String namespace
            , @PathVariable("resourceName") String resourceName) {
        return eventsService.getEventList(namespace, resourceName);
    }

    /**
     * Get events list by node name.
     *
     * @param namespace the namespace
     * @param nodeName  the node name
     * @return the event list
     */
    @GetMapping(value = "/node/{nodeName:.+}")
    public EventsList getEventListByNode(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "nodeName") String nodeName) {
        return eventsService.getEventListByNode(namespace, nodeName);
    }
}
