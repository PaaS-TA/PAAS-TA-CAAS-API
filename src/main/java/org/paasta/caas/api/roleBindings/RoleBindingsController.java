package org.paasta.caas.api.roleBindings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * RoleBinding Controller 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-17
 */
@RestController
@RequestMapping(value = "/namespaces/{namespace:.+}/roleBindings")
public class RoleBindingsController {

    private final RoleBindingsService roleBindingService;

    /**
     * Instantiates a new RoleBinding controller
     *
     * @param roleBindingService the roleBinding service
     */
    @Autowired
    public RoleBindingsController(RoleBindingsService roleBindingService) {
        this.roleBindingService = roleBindingService;
    }

    /**
     * RoleBindingList 객체의 리스트를 조회한다.
     *
     * @param namespace the namespace
     * @return the RoleBindingsList
     */
    @GetMapping
    public RoleBindingsList getRoleBindingList(@PathVariable("namespace") String namespace){
        return roleBindingService.getRoleBindingList(namespace);
    }

    /**
     * RoleBinding 객체를 조회한다.
     *
     * @param namespace the namespace
     * @param roleBindingName the roleBindingName
     * @return the RoleBindings
     */
    @GetMapping(value = "/{roleBindingName:.+}")
    public RoleBindings getRoleBinding(@PathVariable("namespace") String namespace, @PathVariable("roleBindingName") String roleBindingName){
        return roleBindingService.getRoleBinding(namespace, roleBindingName);
    }

    /**
     * RoleBinding 권한을 할당한다.
     *
     * @param namespace the namespace
     * @param roleBinding the roleBinding
     * @return the roleBindings
     */
    @PostMapping
    public RoleBindings createRoleBinding(@PathVariable("namespace") String namespace, @RequestBody RoleBindings roleBinding){
        return roleBindingService.createRoleBinding(namespace, roleBinding);
    }

    /**
     * RoleBinding 권한을 해지한다.
     *
     * @param namespace the namespace
     * @param roleBindingName the roleBindingName
     * @return String
     */
    @DeleteMapping(value = "/{roleBindingName:.+}")
    public String deleteRoleBinding(@PathVariable("namespace") String namespace, @PathVariable("roleBindingName") String roleBindingName){
        return roleBindingService.deleteRoleBinding(namespace, roleBindingName);
    }

    /**
     * RoleBinding 권한을 변경한다.
     *
     * @param namespace the namespace
     * @param roleBindingName the roleBindingName
     * @param yml the yml
     * @return String
     */
    @PutMapping(value = "/{roleBindingName:.+}")
    public String updateRoleBinding(@PathVariable("namespace") String namespace, @PathVariable("roleBindingName") String roleBindingName, @RequestBody String yml){
        return roleBindingService.updateRoleBinding(namespace, roleBindingName, yml);
    }
}
