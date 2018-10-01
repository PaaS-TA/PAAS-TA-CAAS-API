package org.paasta.caas.api.clusters.namespaces;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * ResourceQuotaSpec Model 클래스
 *
 * @author indra
 * @version 1.0
 * @since 2018.08.28
 */
@Data
public class ResourceQuotaSpec {

  private Map<String, String> hard;
  private List<String> scopes;

}

