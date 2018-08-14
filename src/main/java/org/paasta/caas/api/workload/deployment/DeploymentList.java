package org.paasta.caas.api.workload.deployment;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;

import java.util.ArrayList;
import java.util.List;

/**
 * Deployment List Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class DeploymentList {
//    @SerializedName("kind")
//    private String kind = null;

//    @SerializedName("apiVersion")
//    private String apiVersion = null;

    @SerializedName("items")
    private List<Deployment> items = new ArrayList<>();

    @SerializedName("metadata")
    private CommonMetaData metadata = null;
}
