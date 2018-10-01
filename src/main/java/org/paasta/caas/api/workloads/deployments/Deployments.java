package org.paasta.caas.api.workloads.deployments;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.workloads.deployments.support.DeploymentsSpec;
import org.paasta.caas.api.workloads.deployments.support.DeploymentsStatus;

import java.util.Map;

/**
 * Deployments Model 클래스
 *
 * @author PHR
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class Deployments {
    private String resultCode;

    private CommonMetaData metadata;
    private DeploymentsSpec spec;
    private DeploymentsStatus status;

    private Map<String, Object> source;
    private String sourceTypeYaml;

}
