package org.paasta.caas.api.cluster.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Role API 를 호출받는 컨트롤러이다.
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@RestController
@RequestMapping("/cluster")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/namespaces/{namespace}/roles")
    public RoleList getRoleList(@PathVariable("namespace") String namespace){
        return roleService.getRoleList(namespace);
    }
}
