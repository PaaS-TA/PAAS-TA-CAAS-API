package org.paasta.caas.api.clusters.namespace;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonSpec;
import org.paasta.caas.api.common.model.CommonStatus;

import java.util.List;

/**
 * Namespace Model 클래스
 *
 * @author kdh
 * @version 1.0
 * @since 2018.08.28
 */
@Data
public class Namespace {

    private String resultCode;

    private String kind;
    private String apiVersion;

    private CommonMetaData metadata;
    private CommonSpec spec;
    private CommonStatus status;
}
