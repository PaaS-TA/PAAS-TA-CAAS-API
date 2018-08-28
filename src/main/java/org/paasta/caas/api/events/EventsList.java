package org.paasta.caas.api.events;

import lombok.Data;

import java.util.List;

/**
 * Events List Model 클래스
 *
 * @author Ciss
 * @version 1.0
 * @since 2018.8.13
 */
@Data
public class EventsList {

    private String resultCode;
    private List<Events> items;

}
