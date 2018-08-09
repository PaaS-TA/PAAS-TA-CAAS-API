package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Replicaset Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class CommonPodTemplateSpec {

    @SerializedName("metadata")
    private CommonMetaData metadata = null;

    @SerializedName("spec")
    private CommonPodSpec spec = null;

}
