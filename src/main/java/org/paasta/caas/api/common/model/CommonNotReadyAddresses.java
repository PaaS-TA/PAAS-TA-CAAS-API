package org.paasta.caas.api.common.model;

import lombok.Data;

/**
 * Common Not Ready Addresses Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.09.14
 */
@Data
class CommonNotReadyAddresses {
    private String ip;
    private String nodeName;
}
