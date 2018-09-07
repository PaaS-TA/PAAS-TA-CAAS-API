package org.paasta.caas.api.roleBindings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * RoleBinding API 를 호출받는 컨트롤러이다.
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-17
 */
@RestController
@RequestMapping(value = "/roleBindings")
public class RoleBindingsController {

    private final RoleBindingsService roleBindingService;

    @Autowired
    public RoleBindingsController(RoleBindingsService roleBindingService) {
        this.roleBindingService = roleBindingService;
    }

    /**
     * RoleBindingList 객체의 리스트를 조회한다.
     *
     * @param namespace
     * @return Map
     */
    @GetMapping(value = "/namespaces/{namespace}/roleBindings")
    public RoleBindingsList getRoleBindingList(@PathVariable("namespace") String namespace){
        return roleBindingService.getRoleBindingList(namespace);
    }

    /**
     * RoleBinding 객체를 조회한다.
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    @GetMapping(value = "/namespaces/{namespace}/roleBindings/{roleBindingName}")
    public RoleBindings getRoleBinding(@PathVariable("namespace") String namespace, @PathVariable("roleBindingName") String roleBindingName){
        return roleBindingService.getRoleBinding(namespace, roleBindingName);
    }

    /**
     * RoleBinding 권한을 할당한다.
     *
     * @param namespace
     * @param roleBinding
     * @return
     */
    @PostMapping(value = "/namespaces/{namespace}/rolebindings")
    public RoleBindings createRoleBinding(@PathVariable("namespace") String namespace, @RequestBody RoleBindings roleBinding){
        return roleBindingService.createRoleBinding(namespace, roleBinding);
    }

    /**
     * RoleBinding 권한을 해지한다.
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    @DeleteMapping(value = "/namespaces/{namespace}/rolebindings/{roleBindingName}")
    public String deleteRoleBinding(@PathVariable("namespace") String namespace, @PathVariable("roleBindingName") String roleBindingName){
        return roleBindingService.deleteRoleBinding(namespace, roleBindingName);
    }

    /**
     * RoleBinding 권한을 수정한다.
     *
     * @return
     */
    @PutMapping(value = "/namespaces/{namespace}/rolebindings/{roleBindingName}")
    public String updateRoleBinding(@PathVariable("namespace") String namespace, @PathVariable("roleBindingName") String roleBindingName, @RequestBody String yml){
        return roleBindingService.updateRoleBinding(namespace, roleBindingName, yml);
    }
}
