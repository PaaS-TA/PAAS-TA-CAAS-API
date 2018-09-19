package org.paasta.caas.api.workloads.pods.support;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Secret {

    @SerializedName(value = "secretName")
    private String secretName;

    @SerializedName(value = "defaultMode")
    private String defaultMode;
}
