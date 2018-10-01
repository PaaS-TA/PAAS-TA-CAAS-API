package org.paasta.caas.api.workloads.deployments.support;

import lombok.Data;

import org.paasta.caas.api.common.model.CommonLabelSelector;
import org.paasta.caas.api.common.model.CommonPodTemplateSpec;

/**
 * DeploymentsSpec Model 클래스
 *
 * @author PHR
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class DeploymentsSpec {
    private int minReadySeconds;
    private boolean paused;
    private int progressDeadlineSeconds;
    private int replicas;
    private int revisionHistoryLimit;
    private CommonLabelSelector selector;
    private DeploymentsStrategy strategy;
    private CommonPodTemplateSpec template;

}
