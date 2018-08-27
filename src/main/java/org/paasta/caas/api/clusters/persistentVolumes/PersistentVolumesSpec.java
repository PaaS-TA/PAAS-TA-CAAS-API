package org.paasta.caas.api.clusters.persistentVolumes;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonObjectReference;

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
public class PersistentVolumesSpec {

    @SerializedName("accessModes")
    private List<String> accessModes;

    @SerializedName("capacity")
    private Map<String, String> capacity;

    @SerializedName("claimRef")
    private CommonObjectReference claimRef;

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

}
