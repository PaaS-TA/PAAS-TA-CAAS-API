package org.paasta.caas.api.workload.deployment.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonContainer;
import org.paasta.caas.api.common.model.CommonLabelSelector;
import org.paasta.caas.api.common.model.CommonTemplateSpec;

import java.util.*;

/**
 * DeploymentSpec Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class DeploymentSpec {
    @SerializedName("minReadySeconds")
    private int minReadySeconds;

    @SerializedName("paused")
    private boolean paused;

    @SerializedName("progressDeadlineSeconds")
    private int progressDeadlineSeconds;

    @SerializedName("replicas")
    private int replicas;

    @SerializedName("revisionHistoryLimit")
    private int revisionHistoryLimit;

    @SerializedName("selector")
    private CommonLabelSelector selector;

    @SerializedName("strategy")
    private DeploymentStrategy strategy;

    @SerializedName("template")
    private CommonTemplateSpec template;

    @SerializedName( "images" )
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
