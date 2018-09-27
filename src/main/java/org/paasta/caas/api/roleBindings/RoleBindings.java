package org.paasta.caas.api.roleBindings;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;

import java.util.List;
import java.util.Map;

/**
 * RoleBindings Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-17
 */
@Data
public class RoleBindings {

    private String resultCode;

    private String apiVersion;
    private String kind;

    private CommonMetaData metadata;
    private List<Map> subjects;
    private Map<String, String> roleRef;

}
