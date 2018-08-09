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
public class CommonReplicaSetSpec {

    @SerializedName("minReadySeconds")
    private Integer minReadySeconds = null;

    @SerializedName("replicas")
    private Integer replicas = null;

    @SerializedName("selector")
    private CommonLabelSelector selector = null;

    @SerializedName("template")
    private CommonPodTemplateSpec template = null;

}
