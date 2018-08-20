package org.paasta.caas.api.roleBinding;

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
public class RoleBindingController {

    private final RoleBindingService roleBindingService;

    @Autowired
    public RoleBindingController(RoleBindingService roleBindingService) {
        this.roleBindingService = roleBindingService;
    }

    /**
     * RoleBinding 객체의 리스트를 조회한다. (모든 네임스페이스에서 조회)
     *
     * @return
     */
    @GetMapping
    public RoleBindingList getRoleBindingListByAllNamespace(){
        return roleBindingService.getRoleBindingListByAllNamespace();
    }

    /**
     * RoleBindingList 객체의 리스트를 조회한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @return Map
     */
    @GetMapping(value = "/namespaces/{namespace}/roleBindings")
    public RoleBindingList getRoleBindingList(@PathVariable("namespace") String namespace){
        return roleBindingService.getRoleBindingList(namespace);
    }

    /**
     * RoleBinding 객체를 조회한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    @GetMapping(value = "/namespaces/{namespace}/roleBindings/{roleBindingName}")
    public RoleBinding getRoleBinding(@PathVariable("namespace") String namespace, @PathVariable("roleBindingName") String roleBindingName){
        return roleBindingService.getRoleBinding(namespace, roleBindingName);
    }

    /**
     * RoleBinding 권한을 할당한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBinding
     * @return
     */
    @PostMapping(value = "/namespaces/{namespace}/rolebindings")
    public RoleBinding createRoleBinding(@PathVariable("namespace") String namespace, @RequestBody RoleBinding roleBinding){
        return roleBindingService.createRoleBinding(namespace, roleBinding);
    }

    /**
     * RoleBinding 권한을 해지한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace
     * @param roleBindingName
     * @return
     */
    @DeleteMapping(value = "/namespaces/{namespace}/rolebindings/{roleBindingName}")
    public String deleteRoleBinding(@PathVariable("namespace") String namespace, @PathVariable("roleBindingName") String roleBindingName){
        return roleBindingService.deleteRoleBinding(namespace, roleBindingName);
    }
}
