package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Common Condition Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class CommonCondition {

    @SerializedName("type")
    private String type;

    @SerializedName("status")
    private String status;

}
