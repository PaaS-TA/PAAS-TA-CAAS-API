package org.paasta.caas.api.workload.replicaSet;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonReplicaSetSpec;
import org.paasta.caas.api.common.model.CommonStatus;

/**
 * Replicaset Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class Replicaset {

    @SerializedName("metadata")
    private CommonMetaData metadata = null;

    @SerializedName("spec")
    private CommonReplicaSetSpec spec = null;

    @SerializedName("status")
    private CommonStatus status = null;

//    @SerializedName("apiVersion")
//    private String apiVersion = null;

//    @SerializedName("kind")
//    private String kind = null;

}
