package org.paasta.caas.api.role;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonRoleRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Role Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@Data
public class Role {

    private String resultCode;

    private CommonMetaData metadata;
    private List<CommonRoleRule> rules = new ArrayList<>();

}
