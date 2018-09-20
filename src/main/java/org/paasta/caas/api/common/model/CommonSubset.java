package org.paasta.caas.api.common.model;

import lombok.Data;

import java.util.List;

/**
 * Common Subsets Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class CommonSubset {
    private List<CommonAddresses> addresses;
    private List<CommonNotReadyAddresses> notReadyAddresses;
    private List<CommonPort> ports;
}
