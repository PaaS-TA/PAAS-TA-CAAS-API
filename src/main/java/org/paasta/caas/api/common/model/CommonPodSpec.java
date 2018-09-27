package org.paasta.caas.api.common.model;

import lombok.Data;

import java.util.List;

/**
 * CommonPodSpec Model 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class CommonPodSpec {
    private List<CommonContainer> containers;
}
