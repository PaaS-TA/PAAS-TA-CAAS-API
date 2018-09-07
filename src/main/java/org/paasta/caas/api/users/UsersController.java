package org.paasta.caas.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-09-03
 */
@RestController
@RequestMapping(value = "/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @DeleteMapping(value = "/namespaces/{namespace}/serviceaccounts/{caasAccountName}")
    public Users deleteUser(@PathVariable("namespace") String namespace, @PathVariable("caasAccountName") String caasAccountName){
        return usersService.deleteServiceAccount(namespace, caasAccountName);
    }
}
