package org.paasta.caas.api.roleBindings;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-08-17
 */
@Data
public class RoleBindingsList {

    private String resultCode;

    private List<RoleBindings> items = new ArrayList<>();
}
