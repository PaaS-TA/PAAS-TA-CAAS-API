package org.paasta.caas.api.workload.deployment;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.workload.deployment.support.DeploymentSpec;
import org.paasta.caas.api.workload.deployment.support.DeploymentStatus;

/**
 * Deployment Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class Deployment {
//    @SerializedName("kind")
//    private String kind = null;

//    @SerializedName("apiVersion")
//    private String apiVersion = null;

    @SerializedName( "metadata" )
    private CommonMetaData metadata;

    @SerializedName( "spec" )
    private DeploymentSpec spec;

    @SerializedName( "status" )
    private DeploymentStatus status;

    private String resultCode;
    private String resultMessage;
}