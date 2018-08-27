package org.paasta.caas.api.role;

import lombok.Data;
import org.paasta.caas.api.workload.pods.Pods;

import java.util.ArrayList;
import java.util.List;

/**
 * Role List Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@Data
public class RoleList {

    private String resultCode;

    private List<Pods> items = new ArrayList<>();
}
