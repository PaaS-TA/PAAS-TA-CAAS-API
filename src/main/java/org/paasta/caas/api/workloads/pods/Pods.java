package org.paasta.caas.api.workloads.pods;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonSpec;
import org.paasta.caas.api.common.model.CommonStatus;

import java.util.Map;

/**
 * Pods Model 클래스
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class Pods {
    private String resultCode;

    private CommonMetaData metadata;
    private CommonSpec spec;
    private CommonStatus status;
    private String selector;

    private Map<String, Object> source;
    private String sourceTypeYaml;
}
