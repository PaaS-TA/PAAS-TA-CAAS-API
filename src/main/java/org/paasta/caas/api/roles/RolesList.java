package org.paasta.caas.api.roles;

import lombok.Data;

import java.util.List;

/**
 * Role List Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@Data
public class RolesList {

    private String resultCode;
    private List<Roles> items;
}
