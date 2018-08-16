package org.paasta.caas.api.workload.replicaset;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonSpec;
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
    private CommonMetaData metadata;

    @SerializedName("spec")
    private CommonSpec spec;

    @SerializedName("status")
    private CommonStatus status;

    private String resultCode;
    private String resultMessage;

//    @SerializedName("apiVersion")
//    private String apiVersion = null;

//    @SerializedName("kind")
//    private String kind = null;

}
