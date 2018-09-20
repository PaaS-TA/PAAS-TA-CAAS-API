package org.paasta.caas.api.common.model;

import lombok.Data;

/**
 * Common Ports Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class CommonPort {
    private String name;
    private String port;
    private String protocol;
}
