package org.paasta.caas.api.users;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;

import java.util.List;
import java.util.Map;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-09-03
 */
@Data
public class Users {
    private String resultCode;
    private CommonMetaData metadata;
    private List<Map> secrets;
}
