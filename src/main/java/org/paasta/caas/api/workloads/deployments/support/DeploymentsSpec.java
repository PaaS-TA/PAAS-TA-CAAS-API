package org.paasta.caas.api.workloads.deployments.support;

import com.google.gson.annotations.SerializedName;
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

    public Set<String> getImages() {
        CommonPodTemplateSpec podTemplate = getTemplate();
        if (null == podTemplate) {
            return Collections.EMPTY_SET;
        }

        CommonPodSpec podSpec = podTemplate.getSpec();
        if (null == podSpec) {
            return Collections.EMPTY_SET;
        }

        final List<CommonContainer> containers = podSpec.getContainers();
        if (null == containers) {
            return Collections.EMPTY_SET;
        }

        Set<String> images = new HashSet<>(containers.size());
        for (CommonContainer container : containers) {
            images.add(container.getImage());
        }

        return images;
    }
}
