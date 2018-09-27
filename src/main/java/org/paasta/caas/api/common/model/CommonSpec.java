package org.paasta.caas.api.common.model;

import lombok.Data;
import org.paasta.caas.api.workloads.pods.support.Volume;

import java.util.List;
import java.util.Map;

/**
 * Common Spec Model 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class CommonSpec {
    private int minReadySeconds;
    private int replicas;
    private CommonPodTemplateSpec template;
    private long activeDeadlineSeconds;
    private boolean automountServiceAccountToken;
    private String dnsPolicy;
    private boolean hostIPC;
    private boolean hostNetwork;
    private boolean hostPID;
    private String hostname;
    private String nodeName;
    private Map<String, String> nodeSelector;
    private int priority;
    private String priorityClassName;
    private String restartPolicy;
    private String schedulerName;
    private String serviceAccount;
    private String serviceAccountName;
    private boolean shareProcessNamespace;
    private String subdomain;
    private long terminationGracePeriodSeconds;
    private String type;
    private String clusterIP;
    private List ports;
    private String sessionAffinity;
    private Map selector;
    private List<CommonContainer> containers;
    private String podCIDR;
    private List<Map<String, Object>> taints;
    private List<Volume> volumes;
    private List<String> externalIPs;
}
