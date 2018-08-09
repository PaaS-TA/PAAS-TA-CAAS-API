package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Replicaset Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class CommonReplicaSetStatus {

    @SerializedName("availableReplicas")
    private Integer availableReplicas = null;

    @SerializedName("fullyLabeledReplicas")
    private Integer fullyLabeledReplicas = null;

    @SerializedName("observedGeneration")
    private Long observedGeneration = null;

    @SerializedName("readyReplicas")
    private Integer readyReplicas = null;

    @SerializedName("replicas")
    private Integer replicas = null;

    //@SerializedName("conditions")
    //private List<ReplicaSetCondition> conditions = null;
}
