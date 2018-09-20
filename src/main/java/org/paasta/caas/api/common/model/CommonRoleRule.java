package org.paasta.caas.api.common.model;

import lombok.Data;

import java.util.List;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-08-17
 */
@Data
public class CommonRoleRule {
    private List<String> apiGroups;
    private List<String> resources;
    private List<String> verbs;
}
