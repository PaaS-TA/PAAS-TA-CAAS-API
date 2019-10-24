package org.paasta.caas.api.common.model;

import lombok.Data;

/**
 * TypedLocalObjectReference Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-10-24
 */
@Data
public class CommonTypedLocalObjectReference {
    private String apiGroup;
    private String kind;
    private String name;
}
