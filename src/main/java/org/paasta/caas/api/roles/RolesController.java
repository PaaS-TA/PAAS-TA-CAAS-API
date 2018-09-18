package org.paasta.caas.api.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Role Controller 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@RestController
@RequestMapping(value = "/namespaces/{namespace:.+}/roles")
public class RolesController {

    private final RolesService roleService;

    /**
     * Instantiates a new Roles controller
     *
     * @param roleService the role service
     */
    @Autowired
    public RolesController(RolesService roleService) {
        this.roleService = roleService;
    }


//    /**
//     * Role 객체의 리스트를 조회한다.
//     *
//     * @param namespace 조회 대상 네임스페이스
//     * @return RoleList
//     */
//    @GetMapping(value = "/namespaces/{namespace}/roles")
//    public RolesList getRoleList(@PathVariable("namespace") String namespace){
//        return roleService.getRoleList(namespace);
//    }
//
//    /**
//     * Role 객체를 조회한다.
//     *
//     * @param namespace 조회 대상 네임스페이스
//     * @param roleName 조회 대상 role 이름
//     * @return Role
//     */
//    @GetMapping(value = "/namespaces/{namespace}/roles/{roleName}")
//    public Roles getRole(@PathVariable("namespace") String namespace, @PathVariable("roleName") String roleName){
//        return roleService.getRole(namespace, roleName);
//    }


    /**
     * Role 을 삭제한다.
     *
     * @param namespace the namespace
     * @param roleName the roleName
     * @return String
     */
    @DeleteMapping(value = "/{roleName:.+}")
    public String deleteRole(@PathVariable("namespace") String namespace, @PathVariable("roleName") String roleName){
        return roleService.deleteRole(namespace, roleName);
    }

    /**
     * Role 을 수정한다.
     *
     * @param namespace the namespace
     * @param roleName the roleName
     * @param yml the yml
     * @return String
     */
    @PutMapping(value = "/{roleName:.+}")
    public String updateRole(@PathVariable("namespace") String namespace, @PathVariable("roleName") String roleName, @RequestBody String yml){
        return roleService.updateRole(namespace, roleName, yml);
    }
}
