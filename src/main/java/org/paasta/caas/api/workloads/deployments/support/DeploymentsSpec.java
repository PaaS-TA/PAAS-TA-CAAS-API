package org.paasta.caas.api.workloads.deployments.support;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonContainer;
import org.paasta.caas.api.common.model.CommonLabelSelector;
import org.paasta.caas.api.common.model.CommonPodSpec;
import org.paasta.caas.api.common.model.CommonPodTemplateSpec;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DeploymentsSpec Model 클래스
 *
 * @author Hyungu Cho
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
    private Set<String> images;

    public Set<String> getImages() {
        final List<CommonContainer> containers = getTemplate().getSpec().getContainers();
        if (null != containers) {
            final Set<String> imageSet = new HashSet<>(containers.size());
            for (CommonContainer container : containers)
                imageSet.add( container.getImage() );

            return imageSet;
        } else {
            return Collections.EMPTY_SET;
        }
    }
}
