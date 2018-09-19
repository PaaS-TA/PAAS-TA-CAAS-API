package org.paasta.caas.api.workloads.pods.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Common OwnerReferences Model 클래스
 *
 * @author hyerin
 * @version 1.0
 * @since 2018.09.19
 */
@Data
public class OwnerReferences {

    @SerializedName(value = "name")
    private String name;

}