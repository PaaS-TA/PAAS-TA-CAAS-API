package org.paasta.caas.api.common.model;

import lombok.Data;

import java.util.Map;

/**
 * Common Resource Requirement Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.16
 */
@Data
public class CommonResourceRequirement {
    private Map<String, Object> limits;
    private Map<String, Object> requests;
}
