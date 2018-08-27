package org.paasta.caas.api.common.model;

import lombok.Data;

/**
 * CommonPodTemplateSpec Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class CommonPodTemplateSpec {
    private CommonMetaData metadata;
    private CommonPodSpec spec;
}
