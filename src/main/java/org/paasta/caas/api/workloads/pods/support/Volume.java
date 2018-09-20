package org.paasta.caas.api.workloads.pods.support;

import lombok.Data;

/**
 * Volume Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.09.04
 */
@Data
public class Volume {
    private String name;
    private SecretVolumeSource secret;
}