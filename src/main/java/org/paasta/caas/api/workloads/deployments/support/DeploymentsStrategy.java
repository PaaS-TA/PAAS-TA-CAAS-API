package org.paasta.caas.api.workloads.deployments.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * DeploymentsStrategy Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class DeploymentsStrategy {
    @SerializedName("type")
    private String type;

    @SerializedName("rollingUpdate")
    private RollingUpdateDeployments rollingUpdate;
}
