package org.paasta.caas.api.workloads.replicaSets;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import java.util.List;

/**
 * ReplicaSets Model 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class ReplicaSetsList {

    private String kind;
    private String apiVersion;
    private CommonMetaData metadata;
    private List<ReplicaSets> items;
    private String resultCode;
}
