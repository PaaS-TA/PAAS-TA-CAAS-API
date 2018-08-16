package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Common Ports Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class CommonPort {

    // FOR ENDPOINT :: BEGIN
    @SerializedName("name")
    private  String name;

    @SerializedName("port")
    private  String port;

    @SerializedName("protocol")
    private  String protocol;
    // FOR ENDPOINT :: BEGIN

}
