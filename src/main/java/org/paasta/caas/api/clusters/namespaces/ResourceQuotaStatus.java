package org.paasta.caas.api.clusters.namespaces;

import lombok.Data;

import java.util.HashMap;
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

  public ResourceQuotaStatus hard(Map<String, String> hard) {
    this.hard = hard;
    return this;
  }

  public ResourceQuotaStatus putHardItem(String key, String hardItem) {
    if (this.hard == null) {
      this.hard = new HashMap<String, String>();
    }
    this.hard.put(key, hardItem);
    return this;
  }


  public Map<String, String> getHard() {
    return hard;
  }

  public void setHard(Map<String, String> hard) {
    this.hard = hard;
  }

  public ResourceQuotaStatus used(Map<String, String> used) {
    this.used = used;
    return this;
  }

  public ResourceQuotaStatus putUsedItem(String key, String usedItem) {
    if (this.used == null) {
      this.used = new HashMap<String, String>();
    }
    this.used.put(key, usedItem);
    return this;
  }

}

