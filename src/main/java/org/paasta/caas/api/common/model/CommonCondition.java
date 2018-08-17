package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;

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
    @SerializedName("type")
    private String type;

    @SerializedName("status")
    private String status;
    // FOR POD, NODE :: END

    // TODO :: CHECK VARIABLE TYPE >> LocalDateTime
    @SerializedName("lastTransitionTime")
    private String lastTransitionTime;

    @SerializedName("message")
    private String message;

    @SerializedName("reason")
    private String reason;

    /**
     * DeploymentCondition only variable.
     */
    @SerializedName("lastUpdateTime")
    private LocalDateTime lastUpdateTime;
}
