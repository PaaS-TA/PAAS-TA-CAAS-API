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
public class CommonReplicaset {

    @SerializedName("metadata")
    private CommonMetaData metadata = null;

    @SerializedName("spec")
    private CommonReplicaSetSpec spec = null;

    @SerializedName("status")
    private CommonReplicaSetStatus status = null;

//    @SerializedName("apiVersion")
//    private String apiVersion = null;

//    @SerializedName("kind")
//    private String kind = null;

}
