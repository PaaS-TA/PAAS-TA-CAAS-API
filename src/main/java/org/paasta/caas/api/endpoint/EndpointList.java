package org.paasta.caas.api.endpoint;

import lombok.Data;

import java.util.List;

/**
 * Endpoint List Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.8.13
 */
@Data
public class EndpointList {

    private String resultCode;
    private List<Endpoint> items;

}
