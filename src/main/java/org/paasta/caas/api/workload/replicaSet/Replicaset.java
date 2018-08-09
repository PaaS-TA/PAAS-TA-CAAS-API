package org.paasta.caas.api.workload.replicaSet;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Replicaset Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class Replicaset{
//    @SerializedName("apiVersion")
//    private String apiVersion = null;
//
//    @SerializedName("kind")
//    private String kind = null;

    @SerializedName("metadata")
    private ObjectMeta metadata = null;

//    @SerializedName("spec")
//    private ReplicaSetSpec spec = null;
//
//    @SerializedName("status")
//    private ReplicaSetStatus status = null;
}
