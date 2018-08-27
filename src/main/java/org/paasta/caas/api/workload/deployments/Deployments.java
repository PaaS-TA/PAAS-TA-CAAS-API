package org.paasta.caas.api.workload.deployments;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.workload.deployments.support.DeploymentsSpec;
import org.paasta.caas.api.workload.deployments.support.DeploymentsStatus;

/**
 * Deployments Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class Deployments {
    private String resultCode;

    private CommonMetaData metadata;
    private DeploymentsSpec spec;
    private DeploymentsStatus status;

//    private String kind;
//    private String apiVersion;
}
