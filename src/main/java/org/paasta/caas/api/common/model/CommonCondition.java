package org.paasta.caas.api.common.model;

import lombok.Data;
import org.paasta.caas.api.common.CommonUtils;

/**
 * Common Condition Model 클래스
 *
 * @author REX
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class CommonCondition {
    private String type;
    private String status;
    private String message;
    private String reason;
    private String lastHeartbeatTime;
    private String lastTransitionTime;

    public String getLastHeartbeatTime() {
        return CommonUtils.procSetTimestamp(lastHeartbeatTime);
    }

    public String getLastTransitionTime() {
        return CommonUtils.procSetTimestamp(lastTransitionTime);
    }
}
