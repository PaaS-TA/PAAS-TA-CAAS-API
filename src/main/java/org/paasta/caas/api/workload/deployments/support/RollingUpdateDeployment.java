package org.paasta.caas.api.workload.deployments.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * RollingUpdateDeployment
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class RollingUpdateDeployment {
    @SerializedName("maxSurge")
    private String maxSurge;

    @SerializedName("maxUnavailable")
    private String maxUnavailable;
}
