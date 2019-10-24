package org.paasta.caas.api.persistentVolumeClaims;

import lombok.Data;

import java.util.List;

/**
 * PersistentVolumeClaims List Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-10-24
 */
@Data
public class PersistentVolumeClaimsList {
    private String resultCode;
    private List<PersistentVolumeClaims> items;
}
