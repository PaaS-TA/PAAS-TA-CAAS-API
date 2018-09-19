package org.paasta.caas.api.nodes;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonSpec;
import org.paasta.caas.api.nodes.support.NodesStatus;

/**
 * Nodes Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class Nodes {
    private String resultCode;

    private CommonMetaData metadata;
    private CommonSpec spec;
    private NodesStatus status;
}
