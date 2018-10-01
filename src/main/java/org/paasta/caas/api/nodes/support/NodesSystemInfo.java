package org.paasta.caas.api.nodes.support;

import lombok.Data;

/**
 * Nodes system information model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.09.17
 */
@Data
class NodesSystemInfo {
    private String architecture;
    private String bootID;
    private String containerRuntimeVersion;
    private String kernelVersion;
    private String kubeProxyVersion;
    private String kubeletVersion;
    private String machineID;
    private String operatingSystem;
    private String osImage;
    private String systemUUID;
}
