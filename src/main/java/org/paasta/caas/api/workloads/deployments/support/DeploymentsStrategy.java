package org.paasta.caas.api.workloads.deployments.support;

import lombok.Data;

/**
 * DeploymentsStrategy Model 클래스
 *
 * @author PHR
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class DeploymentsStrategy {
    private String type;
    private RollingUpdateDeployments rollingUpdate;
}
