package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * ObjectReference Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.23
 */
@Data
public class CommonObjectReference {

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("fieldPath")
    private String fieldPath;

    @SerializedName("kind")
    private String kind;

    @SerializedName("name")
    private String name;

    @SerializedName("namespace")
    private String namespace;

    @SerializedName("resourceVersion")
    private String resourceVersion;

    @SerializedName("uid")
    private String uid;

}
