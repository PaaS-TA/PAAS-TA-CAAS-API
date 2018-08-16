package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Common Ports Model 클래스
 *
 * @author REX
 * @author CISS
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class CommonContainer {

    @SerializedName("image")
    private String image;

    // TODO :: REMOVE CommonPorts 와 동일합니다. 확인후 삭제 해주세요.
    /*
    // FOR ENDPOINT :: BEGIN
    @SerializedName("name")
    private  String name;

    @SerializedName("port")
    private  String port;

    @SerializedName("protocol")
    private  String protocol;
    // FOR ENDPOINT :: BEGIN
    */

    // TODO :: REMOVE  guide :항목 추가시 kube client lib 참조하세요. : src/main/java/io/kubernetes/client/models/V1Container.java

}
