package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Map;

/**
 * Replicaset Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class CommonPodSpec {

    @SerializedName("activeDeadlineSeconds")
    private Long activeDeadlineSeconds = null;

    @SerializedName("automountServiceAccountToken")
    private Boolean automountServiceAccountToken = null;

    @SerializedName("dnsPolicy")
    private String dnsPolicy = null;

    @SerializedName("hostIPC")
    private Boolean hostIPC = null;

    @SerializedName("hostNetwork")
    private Boolean hostNetwork = null;

    @SerializedName("hostPID")
    private Boolean hostPID = null;

    @SerializedName("hostname")
    private String hostname = null;

    @SerializedName("nodeName")
    private String nodeName = null;

    @SerializedName("nodeSelector")
    private Map<String, String> nodeSelector = null;

    @SerializedName("priority")
    private Integer priority = null;

    @SerializedName("priorityClassName")
    private String priorityClassName = null;

    @SerializedName("restartPolicy")
    private String restartPolicy = null;

    @SerializedName("schedulerName")
    private String schedulerName = null;

    @SerializedName("serviceAccount")
    private String serviceAccount = null;

    @SerializedName("serviceAccountName")
    private String serviceAccountName = null;

    @SerializedName("shareProcessNamespace")
    private Boolean shareProcessNamespace = null;

    @SerializedName("subdomain")
    private String subdomain = null;

    @SerializedName("terminationGracePeriodSeconds")
    private Long terminationGracePeriodSeconds = null;

//    @SerializedName("tolerations")
//    private List<Toleration> tolerations = null;
//
//    @SerializedName("volumes")
//    private List<Volume> volumes = null;
//
//    @SerializedName("affinity")
//    private V1Affinity affinity = null;
//
//    @SerializedName("securityContext")
//    private PodSecurityContext securityContext = null;
//
//    @SerializedName("imagePullSecrets")
//    private List<V1LocalObjectReference> imagePullSecrets = null;
//
//    @SerializedName("initContainers")
//    private List<V1Container> initContainers = null;
//
//    @SerializedName("containers")
//    private List<Container> containers = new ArrayList<Container>();
//
//    @SerializedName("dnsConfig")
//    private PodDNSConfig dnsConfig = null;
//
//    @SerializedName("hostAliases")
//    private List<HostAlias> hostAliases = null;
}
