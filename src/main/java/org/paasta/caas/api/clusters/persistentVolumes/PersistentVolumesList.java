package org.paasta.caas.api.clusters.persistentVolumes;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;

import java.util.ArrayList;
import java.util.List;

/**
 * persistentvolume Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class PersistentVolumesList {

    @SerializedName("kind")
    private String kind = null;

    @SerializedName("apiVersion")
    private String apiVersion = null;

    @SerializedName("metadata")
    private CommonMetaData metadata = null;

    @SerializedName("items")
    private List<PersistentVolumes> items = new ArrayList<>();

    private String resultCode;
    private String resultMessage;
}
