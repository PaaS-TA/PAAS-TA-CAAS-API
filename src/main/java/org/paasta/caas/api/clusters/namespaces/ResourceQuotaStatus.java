package org.paasta.caas.api.clusters.namespaces;

import lombok.Data;

import java.util.Map;

/**
 * ResourceQuotaStatus Model 클래스
 *
 * @author indra
 * @version 1.0
 * @since 2018.08.28
 */
@Data
public class ResourceQuotaStatus {

    private Map<String, String> hard;
    private Map<String, String> used;

}
