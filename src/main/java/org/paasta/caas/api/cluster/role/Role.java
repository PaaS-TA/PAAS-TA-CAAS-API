package org.paasta.caas.api.cluster.role;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonSpec;
import org.paasta.caas.api.common.model.CommonStatus;

/**
 * Role Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@Data
public class Role {

    private String result;
    private String resultMessage;

    private CommonMetaData metadata;
    private CommonSpec spec;
    private CommonStatus status;

    // FOR DASHBOARD
    private String selector;
}
