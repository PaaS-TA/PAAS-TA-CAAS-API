package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Common Addresses Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class CommonAddresses {

    // FOR ENDPOINT :: BEGIN
    @SerializedName("ip")
    private  String ip;

    @SerializedName("nodeName")
    private  String nodeName;
    // FOR ENDPOINT :: BEGIN

}
