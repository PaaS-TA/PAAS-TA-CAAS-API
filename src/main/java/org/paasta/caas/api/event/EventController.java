package org.paasta.caas.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Event Controller 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.13
 */
@RestController
@RequestMapping("/namespaces/{namespace:.+}/events")
public class EventController {

    private final EventService eventService;

    /**
     * Instantiates a new event controller.
     *
     * @param eventService the event service
     */
    @Autowired
    public EventController(EventService eventService) {this.eventService = eventService;}


    /**
     * Gets Event list.
     * @param namespace the namespace
     * @param resourceName the resourceName
     * @return the event list
     */
    @GetMapping(value = "/resource/{resourceName:.+}")
    public EventList getEventList(@PathVariable("namespace") String namespace
            , @PathVariable("resourceName") String resourceName) {
        return eventService.getEventList(namespace, resourceName);
    }

}
