package org.paasta.caas.api.workloads.pods.support;

import lombok.Data;

/**
 * Secret Volume Source Model 클래스 (Secret 클래스가 아님)
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.09.04
 */
@Data
class SecretVolumeSource {
    private String secretName;
    private String defaultMode;
}
