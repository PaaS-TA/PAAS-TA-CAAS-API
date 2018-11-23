package org.paasta.caas.api.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param resourceUid the resourceUid
     * @return the events list
     */
    @GetMapping(value = "/resources/{resourceUid:.+}")
    public EventsList getEventsList(@PathVariable("namespace") String namespace, @PathVariable("resourceUid") String resourceUid, @RequestParam(value="type", required=false) String type) {
        return eventsService.getEventsList(namespace, resourceUid, type);
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
