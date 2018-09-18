package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Common Status Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class CommonStatus {

    @SerializedName("availableReplicas")
    private int availableReplicas;

    @SerializedName("fullyLabeledReplicas")
    private int fullyLabeledReplicas;

    @SerializedName("observedGeneration")
    private long observedGeneration;

    @SerializedName("readyReplicas")
    private int readyReplicas;

    @SerializedName("replicas")
    private int replicas;

    @SerializedName("phase")
    private String phase;

    @SerializedName("containerStatuses")
    private List containerStatuses;

    private List<CommonCondition> conditions;

    @SerializedName("podIP")
    private String podIP;

    @SerializedName("qosClass")
    private String qosClass;
}
