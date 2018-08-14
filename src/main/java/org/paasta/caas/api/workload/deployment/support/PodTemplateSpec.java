package org.paasta.caas.api.workload.deployment.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonSpec;

/**
 * PodTemplateSpec Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class PodTemplateSpec {
    @SerializedName("metadata")
    private CommonMetaData metadata = null;

    @SerializedName("spec")
    private CommonSpec spec = null;
}
