package org.paasta.caas.api.workloads.replicaSets;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;

import java.util.ArrayList;
import java.util.List;

/**
 * ReplicaSets Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class ReplicaSetsList {

    @SerializedName("kind")
    private String kind = null;

    @SerializedName("apiVersion")
    private String apiVersion = null;

    @SerializedName("metadata")
    private CommonMetaData metadata = null;

    @SerializedName("items")
    private List<ReplicaSets> items = new ArrayList<ReplicaSets>();

    private String resultCode;
}
