package org.paasta.caas.api.workloads.replicaSets;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonSpec;
import org.paasta.caas.api.common.model.CommonStatus;

/**
 * ReplicaSets Model 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class ReplicaSets {

    private String resultCode;
    private CommonMetaData metadata;
    private CommonSpec spec;
    private CommonStatus status;
    private String sourceTypeYaml;

}
