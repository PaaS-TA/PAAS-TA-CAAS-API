package org.paasta.caas.api.workloads.replicaSets;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonSpec;
import org.paasta.caas.api.common.model.CommonStatus;

import java.util.Map;

/**
 * ReplicaSets Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class ReplicaSets {

    @SerializedName("metadata")
    private CommonMetaData metadata;

    @SerializedName("spec")
    private CommonSpec spec;

    @SerializedName("status")
    private CommonStatus status;

    private Map<String, Object> source;

    private String resultCode;

//    @SerializedName("apiVersion")
//    private String apiVersion = null;

//    @SerializedName("kind")
//    private String kind = null;

}
