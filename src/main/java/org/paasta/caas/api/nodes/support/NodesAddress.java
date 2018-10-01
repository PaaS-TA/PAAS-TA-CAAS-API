package org.paasta.caas.api.nodes.support;

import lombok.Data;

/**
 * Nodes address 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.09.17
 */
@Data
class NodesAddress {
    private String address;
    private String type;
}
