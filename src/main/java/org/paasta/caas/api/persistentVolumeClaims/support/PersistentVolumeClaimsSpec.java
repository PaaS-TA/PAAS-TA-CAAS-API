package org.paasta.caas.api.persistentVolumeClaims.support;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonLabelSelector;
import org.paasta.caas.api.common.model.CommonResourceRequirement;
import org.paasta.caas.api.common.model.CommonTypedLocalObjectReference;

import java.util.List;

/**
 * PersistentVolumeClaimsSpec Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-10-24
 */
@Data
public class PersistentVolumeClaimsSpec {
    private List<String> accessModes;
    private String volumeName;
    private String storageClassName;
    private String volumeMode;
    private CommonTypedLocalObjectReference dataSource;
    private CommonResourceRequirement resources;
    private CommonLabelSelector selector;
}
