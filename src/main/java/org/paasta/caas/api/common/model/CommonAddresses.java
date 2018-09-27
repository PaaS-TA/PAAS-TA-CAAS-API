package org.paasta.caas.api.common.model;

import lombok.Data;

/**
 * Common Addresses Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Data
class CommonAddresses {
    private String ip;
    private String nodeName;
}
