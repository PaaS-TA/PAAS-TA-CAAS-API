package org.paasta.caas.api.workload.replicaSet;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Map;

/**
 * Replicaset Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class ObjectMeta {
//    @SerializedName("apiVersion")
//    private String apiVersion = null;
//
//    @SerializedName("kind")
//    private String kind = null;

    @SerializedName("name")
    private String name = null;

    @SerializedName("namespace")
    private String namespace = null;

    @SerializedName("selfLink")
    private String selfLink = null;

    @SerializedName("labels")
    private Map<String, String> labels = null;

}
