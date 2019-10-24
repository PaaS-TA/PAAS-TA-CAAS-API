package org.paasta.caas.api.persistentVolumeClaims.support;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonCondition;

import java.util.List;
import java.util.Map;

/**
 * PersistentVolumeClaimsStatus Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-10-24
 */
@Data
public class PersistentVolumeClaimsStatus {
    private List<String> accessModes;
    private Map<String, Object> capacity;
    private List<CommonCondition> conditions;
    private String phase;
}
