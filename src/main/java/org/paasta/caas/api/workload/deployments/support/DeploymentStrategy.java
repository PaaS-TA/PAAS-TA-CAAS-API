package org.paasta.caas.api.workload.deployments.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * DeploymentStrategy Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class DeploymentStrategy {
    @SerializedName("type")
    private String type;

    @SerializedName("rollingUpdate")
    private RollingUpdateDeployment rollingUpdate;
}
