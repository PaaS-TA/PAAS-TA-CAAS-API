package org.paasta.caas.api.roles;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonRoleRule;

import java.util.List;

/**
 * Role Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@Data
public class Roles {

    private String resultCode;
    private CommonMetaData metadata;
    private List<CommonRoleRule> rules;

}
