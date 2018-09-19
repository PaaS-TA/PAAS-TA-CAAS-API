package org.paasta.caas.api.clusters.namespaces;

import lombok.Data;

import java.util.List;

/**
 * ResourceQuotaList Model 클래스
 *
 * @author indra
 * @version 1.0
 * @since 2018.08.28
 */
@Data
public class ResourceQuotaList {

  private String resultCode;
  private List<ResourceQuota> items;

}

