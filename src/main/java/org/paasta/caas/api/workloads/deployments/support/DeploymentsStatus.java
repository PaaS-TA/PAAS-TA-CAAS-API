package org.paasta.caas.api.workloads.deployments.support;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonCondition;

import java.util.List;

/**
 * DeploymentsStatus Model 클래스
 *
 * @author PHR
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class DeploymentsStatus {
    private int availableReplicas;
    private int collisionCount;
    private List<CommonCondition> conditions;
    private long observedGeneration;
    private int readyReplicas;
    private int replicas;
    private int unavailableReplicas;
    private int updatedReplicas;
}
