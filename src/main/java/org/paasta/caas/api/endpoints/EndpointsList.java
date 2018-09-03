package org.paasta.caas.api.endpoints;

import lombok.Data;

import java.util.List;

/**
 * Endpoints List Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.8.13
 */
@Data
public class EndpointsList {

    private String resultCode;
    private List<Endpoints> items;

}
