package org.paasta.caas.api.cluster.persistentvolume;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonContainer;
import org.paasta.caas.api.common.model.CommonPodTemplateSpec;

import java.util.List;
import java.util.Map;

/**
 * PersistentVolume Spec Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class PersistentVolumeSpec {

    @SerializedName("accessModes")
    private List<String> accessModes;

    @SerializedName("capacity")
    private Map<String, String> capacity;

    @SerializedName("claimRef")
    private ObjectReference claimRef;

    @SerializedName("hostPath")
    private HostPathVolumeSource hostPath;

    @SerializedName("persistentVolumeReclaimPolicy")
    private String persistentVolumeReclaimPolicy;

    @SerializedName("storageClassName")
    private String storageClassName;

    @Data
    public class HostPathVolumeSource {
        @SerializedName("path")
        private String path;

        @SerializedName("type")
        private String type;
    }

    @Data
    public class ObjectReference {
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
}
