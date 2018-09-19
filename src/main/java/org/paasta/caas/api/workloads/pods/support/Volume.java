package org.paasta.caas.api.workloads.pods.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Volume {

    @SerializedName(value = "name")
    private String name;

    @SerializedName(value = "secret")
    private Secret secret;

}