package org.paasta.caas.api.workloads.deployments.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * RollingUpdateDeployments
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class RollingUpdateDeployments {
    @SerializedName("maxSurge")
    private String maxSurge;

    @SerializedName("maxUnavailable")
    private String maxUnavailable;
}
