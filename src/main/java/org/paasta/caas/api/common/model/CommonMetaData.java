package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * ListMeta Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class CommonMetaData {

    @SerializedName("labels")
    private Map<String, String> labels = null;

    @SerializedName("name")
    private String name = null;

    @SerializedName("namespace")
    private String namespace = null;

    @SerializedName("annotations")
    private Map<String, String> annotations = null;

    @SerializedName("clusterName")
    private String clusterName = null;

    @SerializedName("creationTimestamp")
    private String creationTimestamp = null;

    @SerializedName("deletionGracePeriodSeconds")
    private Long deletionGracePeriodSeconds = null;

    @SerializedName("deletionTimestamp")
    private String deletionTimestamp = null;

    @SerializedName("finalizers")
    private List<String> finalizers = null;

    @SerializedName("generateName")
    private String generateName = null;

    @SerializedName("generation")
    private Long generation = null;

    @SerializedName("uid")
    private String uid = null;

    @SerializedName("continue")
    private String _continue = null;

    @SerializedName("resourceVersion")
    private String resourceVersion = null;

    @SerializedName("selfLink")
    private String selfLink = null;


    //@SerializedName("initializers")
    //private V1Initializers initializers = null;

    //@SerializedName("ownerReferences")
    //private List<V1OwnerReference> ownerReferences = null;
}
