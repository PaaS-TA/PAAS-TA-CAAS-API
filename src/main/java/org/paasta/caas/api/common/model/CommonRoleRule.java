package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-08-17
 */
@Data
public class CommonRoleRule {
    @SerializedName(value = "apiGroups")
    private List<String> apiGroups;

    @SerializedName(value = "resources")
    private List<String> resources;

    @SerializedName(value = "verbs")
    private List<String> verbs;
}
