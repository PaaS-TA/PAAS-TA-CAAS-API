package org.paasta.caas.api.event;

import lombok.Data;

import java.util.List;

/**
 * Event List Model 클래스
 *
 * @author Ciss
 * @version 1.0
 * @since 2018.8.13
 */
@Data
public class EventList {

    private String resultCode;
    private List<Event> items;

}
