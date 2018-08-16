package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Common Subsets Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class CommonSubset {

    // FOR ENDPOINT :: BEGIN
    @SerializedName("addresses")
    private List<CommonAddresses> addresses;

    @SerializedName("ports")
    private List<CommonPort> ports;
    // FOR ENDPOINT :: END

}
