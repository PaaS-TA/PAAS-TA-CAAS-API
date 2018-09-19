package org.paasta.caas.api.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.CommonUtils;

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
    private Map<String, String> labels;

    @SerializedName("name")
    private String name;

    @SerializedName("namespace")
    private String namespace;

    @SerializedName("annotations")
    private Map<String, String> annotations;

    @SerializedName("clusterName")
    private String clusterName;

    @SerializedName("creationTimestamp")
    private String creationTimestamp;

    @SerializedName("deletionGracePeriodSeconds")
    private long deletionGracePeriodSeconds;

    @SerializedName("deletionTimestamp")
    private String deletionTimestamp;

    @SerializedName("finalizers")
    private List<String> finalizers;

    @SerializedName("generateName")
    private String generateName;

    @SerializedName("generation")
    private long generation;

    @SerializedName("uid")
    private String uid;

    @SerializedName("continue")
    @JsonProperty("continue")
    private String _continue;

    @SerializedName("resourceVersion")
    private String resourceVersion;

    @SerializedName("selfLink")
    private String selfLink;
    
    @SerializedName("ownerReferences")
    private List<CommonOwnerReferences> ownerReferences;

    public String getCreationTimestamp() {
//        TODO :: REMOVE AFTER CHECK
//        try {
//            this.creationTimestamp = (creationTimestamp != null) ? new SimpleDateFormat(Constants.STRING_DATE_TYPE).format(new SimpleDateFormat(Constants.STRING_ORIGINAL_DATE_TYPE).parse(creationTimestamp)) : null;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return CommonUtils.procSetTimestamp(creationTimestamp);
    }

    public String getDeletionTimestamp() {
        return CommonUtils.procSetTimestamp(deletionTimestamp);
    }
}
