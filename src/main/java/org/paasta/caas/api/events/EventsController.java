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
     * Instantiates a new Events controller.
     *
     * @param eventsService the events service
     */
    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }


    /**
     * Events 목록을 조회한다.
     *
     * @param namespace    the namespace
     * @param resourceName the resourceName
     * @return the events list
     */
    @GetMapping(value = "/resource/{resourceName:.+}")
    public EventsList getEventsList(@PathVariable("namespace") String namespace
            , @PathVariable("resourceName") String resourceName) {
        return eventsService.getEventsList(namespace, resourceName);
    }

    /**
     * Events 목록을 조회한다.(for namespace)
     *
     * @param namespace    the namespace
     * @return the events list
     */
    @GetMapping
    public EventsList getNamespaceEventsList(@PathVariable("namespace") String namespace) {
        return eventsService.getNamespaceEventsList(namespace);
    }
}
