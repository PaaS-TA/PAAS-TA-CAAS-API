package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.paasta.caas.api.common.CommonUtils;

import java.util.List;
import java.util.Map;

/**
 * ListMeta Model 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class CommonMetaData {
    private Map<String, String> labels;
    private String name;
    private String namespace;
    private Map<String, String> annotations;
    private String clusterName;
    private String creationTimestamp;
    private long deletionGracePeriodSeconds;
    private String deletionTimestamp;
    private List<String> finalizers;
    private String generateName;
    private long generation;
    private String uid;
    private String resourceVersion;
    private String selfLink;
    private List<CommonOwnerReferences> ownerReferences;

    @Accessors(prefix = "_")
    @SerializedName("continue")
    private String _continue;

    public String getCreationTimestamp() {
        return CommonUtils.procSetTimestamp(creationTimestamp);
    }

    public String getDeletionTimestamp() {
        return CommonUtils.procSetTimestamp(deletionTimestamp);
    }
}
