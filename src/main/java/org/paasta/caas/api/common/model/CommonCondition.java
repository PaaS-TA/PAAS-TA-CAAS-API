package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
    @SerializedName("type")
    private String type;

    @SerializedName("status")
    private String status;
    
    @SerializedName("lastTransitionTime")
    private LocalDateTime lastTransitionTime;

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
