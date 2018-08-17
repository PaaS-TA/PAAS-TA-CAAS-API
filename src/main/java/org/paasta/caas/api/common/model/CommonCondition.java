package org.paasta.caas.api.common.model;

import lombok.Data;

/**
 * Common Condition Model 클래스
 *
 * @author REX
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class CommonCondition {

    /**
     * <pre>
     * DeploymentConditionType : [ Available, Progressing, ReplicaFailure ]
     * ReplicaSetConditionType : [ ReplicaFailure ]
     * DaemonSetConditionType : [ ]
     * </pre>
     */
    // FOR POD, NODE :: BEGIN
    private String type;
    private String status;
    // FOR POD, NODE :: END

    private String message;
    private String reason;

//
//    private String lastTransitionTime;
//    /* DeploymentCondition only variable. */
//    private LocalDateTime lastUpdateTime;
}
