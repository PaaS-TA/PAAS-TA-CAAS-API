package org.paasta.caas.api.workloads.pods.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Volume Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.09.04
 */
@Data
public class Volume {

    @SerializedName(value = "name")
    private String name;

    @SerializedName(value = "secret")
    private SecretVolumeSource secret;

}