package org.paasta.caas.api.nodes.support;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonCondition;

import java.util.List;
import java.util.Map;

/**
 * Nodes status model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.17
 */
@Data
public class NodesStatus {
    private Map<String, Object> capacity;
    private Map<String, Object> allocatable;
    private List<CommonCondition> conditions;
    private List<NodesAddress> addresses;
    private NodesSystemInfo nodeInfo;
}
