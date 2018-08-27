package org.paasta.caas.api.workloads.deployments.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonCondition;

import java.util.List;

/**
 * DeploymentsStatus Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class DeploymentsStatus {
    @SerializedName("availableReplicas")
    private int availableReplicas;

    @SerializedName("collisionCount")
    private int collisionCount;

    @SerializedName("conditions")
    private List<CommonCondition> conditions;

    @SerializedName("observedGeneration")
    private long observedGeneration;

    @SerializedName("readyReplicas")
    private int readyReplicas;

    @SerializedName("replicas")
    private int replicas;

    @SerializedName("unavailableReplicas")
    private int unavailableReplicas;

    @SerializedName("updatedReplicas")
    private int updatedReplicas;
}
